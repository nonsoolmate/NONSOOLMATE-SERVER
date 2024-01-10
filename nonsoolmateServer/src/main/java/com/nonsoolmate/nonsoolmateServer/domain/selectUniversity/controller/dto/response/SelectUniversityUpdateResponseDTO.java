package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response;

public record SelectUniversityUpdateResponseDTO(
        boolean isSelected
) {
    static public SelectUniversityUpdateResponseDTO of(boolean isSelected) {
        return new SelectUniversityUpdateResponseDTO(isSelected);
    }
}
