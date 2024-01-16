package com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.request;


import jakarta.validation.constraints.NotNull;


public record MemberRequestDTO(
        @NotNull
        String platformType) {

}