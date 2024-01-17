package com.nonsoolmate.nonsoolmateServer.global.security.filter;


import static com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthExceptionType.INVALID_ACCESS_TOKEN;
import static com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthExceptionType.UNAUTHORIZED_ACCESS_TOKEN;
import static com.nonsoolmate.nonsoolmateServer.global.security.config.SecurityConfig.AUTH_WHITELIST;
import static com.nonsoolmate.nonsoolmateServer.global.security.config.SecurityConfig.AUTH_WHITELIST_WILDCARD;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthException;
import com.nonsoolmate.nonsoolmateServer.global.jwt.service.JwtService;
import com.nonsoolmate.nonsoolmateServer.global.security.service.MemberAuthService;
import com.nonsoolmate.nonsoolmateServer.global.jwt.utils.RequestUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
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

        if (Arrays.stream(AUTH_WHITELIST)
                .anyMatch(whiteUrl -> request.getRequestURI().equals(whiteUrl))) {
            filterChain.doFilter(request, response);
            return;
        }

        if (Arrays.stream(AUTH_WHITELIST_WILDCARD)
                .anyMatch(
                        whiteUrl -> request.getRequestURI().startsWith(whiteUrl.substring(0, whiteUrl.length() - 3)))) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!RequestUtils.isContainsAccessToken(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationAccessToken = RequestUtils.getAuthorizationAccessToken(request);

        try {
            jwtService.validateToken(authorizationAccessToken);

            Long memberId = jwtService.extractMemberIdFromAccessToken(authorizationAccessToken);
            UserDetails userDetails = memberAuthService.loadUserByUsername(String.valueOf(memberId));

            Authentication authentication
                    = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            log.info("Authentication Principal : {}", authentication.getPrincipal().toString());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JsonProcessingException | MalformedJwtException e) {
            throw new AuthException(INVALID_ACCESS_TOKEN);
        } catch (ExpiredJwtException e){
            throw new AuthException(UNAUTHORIZED_ACCESS_TOKEN);
        }
        catch (AuthException e) {
            throw new AuthException(e.getExceptionType());
        }

        filterChain.doFilter(request, response);
    }

}
