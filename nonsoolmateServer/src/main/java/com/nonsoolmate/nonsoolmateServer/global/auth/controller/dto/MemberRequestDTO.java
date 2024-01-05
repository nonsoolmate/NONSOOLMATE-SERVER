package com.nonsoolmate.nonsoolmateServer.global.auth.controller.dto;

public record MemberRequestDTO(String email, String memberName, String platformType, String gender, int Year,
                               String phoneNumber) {
}