package com.nonsoolmate.nonsoolmateServer.global.jwt.service;


import static com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthExceptionType.INVALID_ACCESS_TOKEN;
import static com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthExceptionType.INVALID_REFRESH_TOKEN;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.response.MemberAuthResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.response.MemberReissueResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthException;
import com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthExceptionType;
import com.nonsoolmate.nonsoolmateServer.domain.auth.service.vo.MemberSignUpVO;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.enums.Role;
import com.nonsoolmate.nonsoolmateServer.domain.member.repository.MemberRepository;
import com.nonsoolmate.nonsoolmateServer.external.redis.repository.RedisTokenRepository;
import com.nonsoolmate.nonsoolmateServer.global.jwt.service.vo.RefreshTokenVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
@Transactional(readOnly = true)
public class JwtService {
    private static final String AUTH_USER = "memberId";
    private static final String BEARER = "Bearer ";
    private static final String EMAIL_CLAIM = "email";
    private static final String MEMBER_ID_CLAIM = "memberId";


    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTokenRepository redisTokenRepository;

    @Transactional
    public MemberAuthResponseDTO issueToken(MemberSignUpVO vo, HttpServletResponse response) {
        String accessToken = jwtTokenProvider.createAccessToken(vo.email(), vo.memberId(), accessTokenExpirationPeriod);

        if (vo.role().equals(Role.USER)) {
            String refreshToken = jwtTokenProvider.createRefreshToken(refreshTokenExpirationPeriod);
            updateRefreshTokenByMemberId(vo.memberId(), refreshToken);
            return MemberAuthResponseDTO.of(vo.memberId(), vo.authType(), vo.name(), accessToken, refreshToken);
        }

        throw new AuthException(AuthExceptionType.UNAUTHORIZED_MEMBER_LOGIN);
    }

    public MemberReissueResponseDTO reissueToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = extractRefreshToken(request);
        String accessToken = extractAccessToken(request);

        if (!validateToken(refreshToken)) {
            throw new AuthException(INVALID_REFRESH_TOKEN);
        }

        Claims tokenClaims = jwtTokenProvider.getTokenClaims(accessToken);
        RefreshTokenVO foundRefreshToken = redisTokenRepository.findByMemberIdOrElseThrowException(
                String.valueOf(tokenClaims.get(MEMBER_ID_CLAIM)));

        if (!foundRefreshToken.getRefreshToken().equals(refreshToken)) {
            throw new AuthException(INVALID_REFRESH_TOKEN);
        }

        Integer o = (Integer) tokenClaims.get(MEMBER_ID_CLAIM);
        Long memberId = o.longValue();
        String email = (String) tokenClaims.get(EMAIL_CLAIM);

        String newAccessToken = jwtTokenProvider.createAccessToken(email, memberId, accessTokenExpirationPeriod);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(refreshTokenExpirationPeriod);

        updateRefreshTokenByMemberId(memberId, newRefreshToken);

        return MemberReissueResponseDTO.of(memberId, newAccessToken, newRefreshToken);
    }


    public Long extractMemberIdFromAccessToken(final String atk) throws JsonProcessingException {
        Claims tokenClaims = jwtTokenProvider.getTokenClaims(atk);
        return jwtTokenProvider.getMemberIdFromClaim(tokenClaims, AUTH_USER);
    }

    public Boolean validateToken(final String atk) {
        try {
            Claims tokenClaims = jwtTokenProvider.getTokenClaims(atk);
            return !tokenClaims.getExpiration().before(new Date());
        } catch (MalformedJwtException e) {
            throw new AuthException(INVALID_ACCESS_TOKEN);
        }
    }

    private String extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""))
                .orElseThrow(() -> new AuthException(INVALID_REFRESH_TOKEN));
    }

    private String extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""))
                .orElseThrow(() -> new AuthException(INVALID_ACCESS_TOKEN));
    }


    @Transactional
    public void updateRefreshTokenByMemberId(Long memberId, String newRefreshToken) {
        redisTokenRepository.findByMemberId(String.valueOf(memberId))
                .ifPresent(refreshToken -> {
                    refreshToken.updateBlack(true);
                });
        log.info("newRefreshToken = {}", newRefreshToken);
        redisTokenRepository.save(RefreshTokenVO.builder()
                .memberId(String.valueOf(memberId))
                .black(false)
                .refreshToken(newRefreshToken)
                .build());
    }
}