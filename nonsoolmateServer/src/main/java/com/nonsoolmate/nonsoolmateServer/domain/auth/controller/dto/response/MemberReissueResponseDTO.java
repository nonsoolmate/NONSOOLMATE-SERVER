package com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.response;

import com.nonsoolmate.nonsoolmateServer.external.oauth.service.vo.enums.AuthType;
import jakarta.validation.constraints.NotNull;

public record MemberReissueResponseDTO(@NotNull Long memberId, @NotNull AuthType authType,
                                       @NotNull String accessToken, @NotNull String refreshToken) {
    public static MemberReissueResponseDTO of(Long memberId, AuthType authType, String accessToken,
                                           String refreshToken) {
        return new MemberReissueResponseDTO(memberId, authType, accessToken, refreshToken);
    }
}
