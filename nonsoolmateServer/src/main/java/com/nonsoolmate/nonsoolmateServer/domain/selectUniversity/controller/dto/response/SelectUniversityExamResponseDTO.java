package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response;

public record SelectUniversityExamResponseDTO(Long examId, String examName, int examTimeLimit,
                                              String examStatus) {
    public static SelectUniversityExamResponseDTO of(Long examId, String examName, int examTimeLimit,
                                                     String examStatus) {
        return new SelectUniversityExamResponseDTO(examId, examName, examTimeLimit, examStatus);
    }
}
