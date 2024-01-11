package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto;

public record UniversityExamRecordResultResponseDTO(
        String examResultUrl
) {
    public static UniversityExamRecordResultResponseDTO of(String examResultUrl) {
        return new UniversityExamRecordResultResponseDTO(examResultUrl);
    }
}
