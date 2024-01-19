package com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record UniversityExamInfoResponseDTO(@Schema(description = "시험 id", example = "1") Long examId,
                                            @Schema(description = "시험 이름(대학 + 시험 년도 + 시험 이름)", example = "건국대학교 - 2021 인문사회 1") String examName,
                                            @Schema(description = "시험 제한 시간 (초)", example = "6000") int examTimeLimit) {
    public static UniversityExamInfoResponseDTO of(final Long examId, final String examName, final int examTimeLimit) {
        return new UniversityExamInfoResponseDTO(examId, examName, examTimeLimit);
    }
}
