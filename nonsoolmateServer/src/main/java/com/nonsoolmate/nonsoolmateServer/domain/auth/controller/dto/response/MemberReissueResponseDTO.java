package com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "MemberReissueResponseDTO", description = "리프레시 토큰 재발급 응답 DTO")
public record MemberReissueResponseDTO(@NotNull @Schema(description = "사용자 id", example = "1") Long memberId,
                                       @NotNull @Schema(description = "액세스 토큰") String accessToken,
                                       @NotNull @Schema(description = "리프레시 토큰") String refreshToken) {
    public static MemberReissueResponseDTO of(Long memberId, String accessToken,
                                              String refreshToken) {
        return new MemberReissueResponseDTO(memberId, accessToken, refreshToken);
    }
}
