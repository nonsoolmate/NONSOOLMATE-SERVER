package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto;

public record UniversityExamRecordSheetResponseDTO(
        String examResultUrl
) {
    public static UniversityExamRecordSheetResponseDTO of(String examResultUrl) {
        return new UniversityExamRecordSheetResponseDTO(examResultUrl);
    }
}
