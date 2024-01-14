package com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.response;

import java.util.List;

public record UniversityExamImageAndAnswerResponseDTO(
        String universityExamName, List<UniversityExamImageResponseDTO> examQuestionList, String examAnswerUrl
) {
    static public UniversityExamImageAndAnswerResponseDTO of(String universityExamName,
                                                             List<UniversityExamImageResponseDTO> examQuestionList,
                                                             String examAnswerUrl) {
        return new UniversityExamImageAndAnswerResponseDTO(universityExamName, examQuestionList, examAnswerUrl);
    }
}

