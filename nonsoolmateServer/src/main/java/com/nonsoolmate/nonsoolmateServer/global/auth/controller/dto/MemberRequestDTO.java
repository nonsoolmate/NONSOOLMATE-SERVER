package com.nonsoolmate.nonsoolmateServer.global.auth.controller.dto;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.enums.PlatformType;
import jakarta.validation.constraints.NotNull;

public record MemberRequestDTO(@NotNull PlatformType platformType) {
}