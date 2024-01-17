package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UniversityExamSheetPreSignedUrlResponseDTO", description = "답안지 업로드 PresignedUrl 조회 응답 DTO")
public record UniversityExamSheetPreSignedUrlResponseDTO(
        @Schema(description = "시험보기: [3] 답안지 업로드 후 시험 기록 API 에서 다시 서버로 보내야하는 파일 이름") String resultFileName,
        @Schema(description = "시험보기: [2] 답안지 업로드 API를 요청할 URL") String preSignedUrl) {
    public static UniversityExamSheetPreSignedUrlResponseDTO of(String resultFileName, String preSignedUrl) {
        return new UniversityExamSheetPreSignedUrlResponseDTO(resultFileName, preSignedUrl);
    }
}
