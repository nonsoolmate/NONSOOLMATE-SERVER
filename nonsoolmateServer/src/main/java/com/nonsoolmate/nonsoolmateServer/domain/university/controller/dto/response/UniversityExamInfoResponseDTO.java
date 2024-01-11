package com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.response;

public record UniversityExamInfoResponseDTO(Long examId, String examName, int examTimeLimit) {
    public static UniversityExamInfoResponseDTO of(Long examId, String examName, int examTimeLimit) {
        return new UniversityExamInfoResponseDTO(examId, examName, examTimeLimit);
    }
}
