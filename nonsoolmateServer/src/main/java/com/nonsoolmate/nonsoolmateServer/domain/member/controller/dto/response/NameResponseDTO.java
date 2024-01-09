package com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response;

public record NameResponseDTO(String memberName) {
    static public NameResponseDTO of(String memberName) {
        return new NameResponseDTO(memberName);
    }
}
