package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record SelectUniversityRequestDTO(
        @NotNull @Schema(description="사용자가 선택한 대학 id", example="1") Long universityId
) {
}
