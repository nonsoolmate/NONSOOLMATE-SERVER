package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response;

public record UniversityExamRecordResponseDTO(
        String universityExamName,
        String examAnswerUrl,
        String examResultUrl
) {
    public static UniversityExamRecordResponseDTO of(String universityExamName, String examAnswerUrl, String examResultUrl) {
        return new UniversityExamRecordResponseDTO(universityExamName, examAnswerUrl, examResultUrl);
    }
}
