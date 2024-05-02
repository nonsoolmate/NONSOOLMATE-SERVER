package com.nonsoolmate.nonsoolmateServer.domain.member.entity.enums;

import static com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthExceptionType.INVALID_PLATFORM_TYPE;

import com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthException;
import java.util.Arrays;

public enum PlatformType {
    NAVER, NONE;

    public static PlatformType of(String platformType) {
        return Arrays.stream(PlatformType.values()).filter(type -> type.toString().equals(platformType)).findAny()
                .orElseThrow(() -> new AuthException(INVALID_PLATFORM_TYPE));
    }
}
