package com.nonsoolmate.nonsoolmateServer.domain.auth.controller;

import com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.request.MemberRequestDTO;
import com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.response.MemberAuthResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.response.MemberReissueResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@Tag(name = "auth", description = "인증 관련 API")
public interface AuthApi {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404", content = @Content)
            }
    )

    @Operation(summary = "소셜 로그인", description = "네이버 소셜 로그인을 합니다.")
    ResponseEntity<com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse<MemberAuthResponseDTO>> login(
            @Parameter(description = "인가 코드", required = true) @RequestHeader(value = "authorization-code") final String authorizationCode,
            @Parameter(description = "플랫폼 타입(ex.'NAVER')", required = true) @RequestBody @Valid final MemberRequestDTO request);

    @Operation(summary = "액세스 토큰 & 리프레시 토큰 재발급", description = "액세스 토큰 및 리프레시 토큰을 재발급 받습니다.")
    ResponseEntity<com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse<MemberReissueResponseDTO>> reissue(
            HttpServletRequest request);
}
