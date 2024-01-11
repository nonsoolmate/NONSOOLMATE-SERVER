package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response;

public record UniversityExamSheetPreSignedUrlResponseDTO(String resultFileName, String preSignedUrl) {
    static public UniversityExamSheetPreSignedUrlResponseDTO of(String resultFileName, String preSignedUrl) {
        return new UniversityExamSheetPreSignedUrlResponseDTO(resultFileName, preSignedUrl);
    }
}
