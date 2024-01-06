package com.nonsoolmate.nonsoolmateServer.global.auth.controller.dto.response;

import com.nonsoolmate.nonsoolmateServer.global.auth.service.vo.enums.AuthType;
import jakarta.validation.constraints.NotNull;

public record MemberAuthResponseDTO(@NotNull Long memberId, @NotNull AuthType authType, @NotNull String name) {
    public static MemberAuthResponseDTO of(Long memberId, AuthType authType, String name) {
        return new MemberAuthResponseDTO(memberId, authType, name);
    }
}
