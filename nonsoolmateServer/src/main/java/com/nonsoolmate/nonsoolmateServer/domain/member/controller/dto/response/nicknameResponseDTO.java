package com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response;

public record nicknameResponseDTO(String memberName) {
    static public nicknameResponseDTO of(String memberName) {
        return new nicknameResponseDTO(memberName);
    }
}
