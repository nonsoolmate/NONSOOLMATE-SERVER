package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response;


public record SelectUniversityResponseDTO(
    Long universityId, String universityName, String collegeName, boolean memberStatus
) {
    static public SelectUniversityResponseDTO of(Long universityId, String universityName, String collegeName, boolean memberStatus) {
        return new SelectUniversityResponseDTO(universityId, universityName, collegeName, memberStatus);
    }
}
