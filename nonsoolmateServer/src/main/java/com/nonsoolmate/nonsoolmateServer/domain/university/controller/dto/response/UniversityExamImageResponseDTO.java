package com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.response;

public record UniversityExamImageResponseDTO(String examImgUrl) {
    static public UniversityExamImageResponseDTO of(String examImgUrl) {
        return new UniversityExamImageResponseDTO(examImgUrl);
    }
}
