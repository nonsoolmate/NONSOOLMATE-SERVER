package com.nonsoolmate.nonsoolmateServer.global.auth.service;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.enums.PlatformType;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthServiceProvider {
    private static final Map<PlatformType, AuthService> authServiceMap = new HashMap<>();

    private final NaverAuthService naverAuthService;

    @PostConstruct
    void init() {
        authServiceMap.put(PlatformType.NAVER, naverAuthService);
    }

    public AuthService getAuthService(PlatformType platformType) {
        return authServiceMap.get(platformType);
    }
}
