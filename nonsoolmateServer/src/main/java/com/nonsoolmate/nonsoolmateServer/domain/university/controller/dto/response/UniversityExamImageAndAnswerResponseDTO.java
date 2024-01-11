package com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.response;

import java.util.List;

public record UniversityExamImageAndAnswerResponseDTO(
        String university, String college, List<UniversityExamImageResponseDTO> examQuestionList, String examAnswerUrl
) {
    static public UniversityExamImageAndAnswerResponseDTO of(String university, String college,
                                                             List<UniversityExamImageResponseDTO> examQuestionList,
                                                             String examAnswerUrl) {
        return new UniversityExamImageAndAnswerResponseDTO(university, college, examQuestionList, examAnswerUrl);
    }
}

