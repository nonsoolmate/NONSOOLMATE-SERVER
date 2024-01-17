package com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UniversityExamImageResponseDTO", description = "대학 시험 이미지 조회 요청 DTO")
public record UniversityExamImageResponseDTO(@Schema(description = "시험 문제 이미지 URL") String examImgUrl) {
    static public UniversityExamImageResponseDTO of(String examImgUrl) {
        return new UniversityExamImageResponseDTO(examImgUrl);
    }
}
