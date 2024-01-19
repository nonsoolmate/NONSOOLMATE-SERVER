package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UniversityExamRecordResponseDTO", description = "첨삭 PDF_해제PDF 조회 응답 DTO")
public record UniversityExamRecordResponseDTO(
        @Schema(description = "시험 이름(대학이름+시험년도+시험이름)", example = "건국대학교 - 2021 인문사회 2") String universityExamName,
        @Schema(description = "시험 해제 PDF URL", example = "1") String examAnswerUrl,
        @Schema(description = "시험 첨삭 결과 PDF URL", example = "1") String examResultUrl
) {
    public static UniversityExamRecordResponseDTO of(final String universityExamName, final String examAnswerUrl, final String examResultUrl) {
        return new UniversityExamRecordResponseDTO(universityExamName, examAnswerUrl, examResultUrl);
    }
}
