package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record SelectUniversityRequestDTO(
        @NotNull Long universityId
) {
}
