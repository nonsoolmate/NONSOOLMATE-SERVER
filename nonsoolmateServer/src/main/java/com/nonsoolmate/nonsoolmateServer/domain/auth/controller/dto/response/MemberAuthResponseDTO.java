package com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.response;

import com.nonsoolmate.nonsoolmateServer.external.oauth.service.vo.enums.AuthType;
import jakarta.validation.constraints.NotNull;

public record MemberAuthResponseDTO(@NotNull Long memberId, @NotNull AuthType authType, @NotNull String name) {
    public static MemberAuthResponseDTO of(Long memberId, AuthType authType, String name) {
        return new MemberAuthResponseDTO(memberId, authType, name);
    }
}
