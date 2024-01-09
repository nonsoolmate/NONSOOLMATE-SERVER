package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response;

public record SelectUniversityExamResponseDTO(Long universityId, String examName, int examTimeLimit,
                                              String examStatus) {
    public static SelectUniversityExamResponseDTO of(Long universityId, String examName, int examTimeLimit,
                                                     String examStatus) {
        return new SelectUniversityExamResponseDTO(universityId, examName, examTimeLimit, examStatus);
    }
}
