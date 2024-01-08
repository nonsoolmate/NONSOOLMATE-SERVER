package com.nonsoolmate.nonsoolmateServer.domain.auth.service;

import com.nonsoolmate.nonsoolmateServer.domain.auth.service.AuthService;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.enums.PlatformType;
import com.nonsoolmate.nonsoolmateServer.external.oauth.service.NaverAuthService;
import jakarta.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthServiceProvider {
    private static final ConcurrentHashMap<PlatformType, AuthService> authServiceMap = new ConcurrentHashMap<>();

    private final NaverAuthService naverAuthService;

    @PostConstruct
    void init() {
        authServiceMap.put(PlatformType.NAVER, naverAuthService);
    }

    public AuthService getAuthService(PlatformType platformType) {
        return authServiceMap.get(platformType);
    }
}
