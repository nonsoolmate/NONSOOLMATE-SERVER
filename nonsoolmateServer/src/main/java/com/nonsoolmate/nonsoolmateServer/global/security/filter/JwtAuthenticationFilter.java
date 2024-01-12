package com.nonsoolmate.nonsoolmateServer.global.security.filter;


import static com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthExceptionType.INVALID_ACCESS_TOKEN;
import static com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthExceptionType.UNAUTHORIZED_ACCESS_TOKEN;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthException;
import com.nonsoolmate.nonsoolmateServer.global.jwt.service.JwtService;
import com.nonsoolmate.nonsoolmateServer.global.security.service.MemberAuthService;
import com.nonsoolmate.nonsoolmateServer.global.jwt.utils.RequestUtils;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberAuthService memberAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (!RequestUtils.isContainsAccessToken(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationAccessToken = RequestUtils.getAuthorizationAccessToken((HttpServletRequest) request);

        try {
            if (!jwtService.validateToken(authorizationAccessToken)) {
                throw new AuthException(UNAUTHORIZED_ACCESS_TOKEN);
            }

            Long memberId = jwtService.extractMemberIdFromAccessToken(authorizationAccessToken);
            UserDetails userDetails = memberAuthService.loadUserByUsername(String.valueOf(memberId));

            Authentication authentication
                    = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            log.info("Authentication Principal : {}", authentication.getPrincipal().toString());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JsonProcessingException e) {
            throw new AuthException(INVALID_ACCESS_TOKEN);
        } catch (AuthException e) {
            throw new AuthException(e.getExceptionType());
        }

        filterChain.doFilter(request, response);
    }

}
