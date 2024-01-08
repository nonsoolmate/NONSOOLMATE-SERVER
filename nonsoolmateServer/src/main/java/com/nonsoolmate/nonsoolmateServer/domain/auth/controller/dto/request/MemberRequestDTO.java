package com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.request;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.enums.PlatformType;
import jakarta.validation.constraints.NotNull;

public record MemberRequestDTO(@NotNull PlatformType platformType) {
}