package com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "UniversityExamImageAndAnswerResponseDTO", description = "대학시험 문제이미지_해제PDF 조회 요청 DTO")
public record UniversityExamImageAndAnswerResponseDTO(
        @Schema(description = "시험 이름(대학 + 시험 년도 + 시험 이름)", example = "2023 중앙대학교 경영경제 1") String universityExamName,
        @Schema(description = "시험 문제 이미지 리스트") List<UniversityExamImageResponseDTO> examQuestionList,
        @Schema(description = "이미지 해제 PDF URL") String examAnswerUrl
) {
    static public UniversityExamImageAndAnswerResponseDTO of(String universityExamName,
                                                             List<UniversityExamImageResponseDTO> examQuestionList,
                                                             String examAnswerUrl) {
        return new UniversityExamImageAndAnswerResponseDTO(universityExamName, examQuestionList, examAnswerUrl);
    }
}

