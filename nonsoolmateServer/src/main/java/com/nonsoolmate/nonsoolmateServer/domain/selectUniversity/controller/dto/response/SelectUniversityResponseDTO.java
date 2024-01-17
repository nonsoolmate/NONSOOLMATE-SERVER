package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SelectUniversityResponseDTO", description = "선택 대학 조회 응답 DTO")
public record SelectUniversityResponseDTO(
        @Schema(name = "대학 id", example = "1") Long universityId,
        @Schema(name = "대학 이름", example = "중앙대학교") String universityName,
        @Schema(name = "단과대학 이름", example = "경영경제") String collegeName,
        @Schema(name = "대학 선택 여부", example = "true") boolean memberStatus
) {
    public static SelectUniversityResponseDTO of(Long universityId, String universityName, String collegeName, boolean memberStatus) {
        return new SelectUniversityResponseDTO(universityId, universityName, collegeName, memberStatus);
    }
}
