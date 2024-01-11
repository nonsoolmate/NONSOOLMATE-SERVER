package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto;

public record UniversityExamResultPreSignedUrlResponseDTO(String resultFileName, String preSignedUrl) {
    static public UniversityExamResultPreSignedUrlResponseDTO of(String resultFileName, String preSignedUrl) {
        return new UniversityExamResultPreSignedUrlResponseDTO(resultFileName, preSignedUrl);
    }
}
