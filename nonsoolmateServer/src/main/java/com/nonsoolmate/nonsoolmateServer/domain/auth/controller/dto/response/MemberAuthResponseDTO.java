package com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.response;

import com.nonsoolmate.nonsoolmateServer.external.oauth.service.vo.enums.AuthType;
import jakarta.validation.constraints.NotNull;

public record MemberAuthResponseDTO(@NotNull Long memberId, @NotNull AuthType authType, @NotNull String name,
                                    @NotNull String accessToken, @NotNull String refreshToken) {
    public static MemberAuthResponseDTO of(Long memberId, AuthType authType, String name, String accessToken,
                                           String refreshToken) {
        return new MemberAuthResponseDTO(memberId, authType, name, accessToken, refreshToken);
    }
}
