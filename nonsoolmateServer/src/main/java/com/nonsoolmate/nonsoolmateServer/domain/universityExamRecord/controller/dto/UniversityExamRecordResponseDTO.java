package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto;

public record UniversityExamRecordResponseDTO(
    String examAnswerUrl,
    String examResultUrl
) {
    public static UniversityExamRecordResponseDTO of(String examAnswerUrl, String examResultUrl){
        return new UniversityExamRecordResponseDTO(examAnswerUrl, examResultUrl);
    }
}
