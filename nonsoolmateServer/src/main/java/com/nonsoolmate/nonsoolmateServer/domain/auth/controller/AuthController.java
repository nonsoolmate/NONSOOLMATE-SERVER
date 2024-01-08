package com.nonsoolmate.nonsoolmateServer.domain.auth.controller;

import com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.request.MemberRequestDTO;
import com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.response.MemberAuthResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthSucessType;
import com.nonsoolmate.nonsoolmateServer.global.jwt.service.JwtService;
import com.nonsoolmate.nonsoolmateServer.domain.auth.service.AuthServiceProvider;
import com.nonsoolmate.nonsoolmateServer.domain.auth.service.vo.MemberSignUpVO;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private final AuthServiceProvider authServiceProvider;
    private final JwtService jwtService;

    @PostMapping("/social/login")
    public ResponseEntity<ApiResponse<MemberAuthResponseDTO>> login(
            @RequestHeader(value = "authorization-code") final String authorizationCode,
            @RequestBody @Valid final
            MemberRequestDTO request, HttpServletResponse response) {
        MemberSignUpVO vo = authServiceProvider.getAuthService(request.platformType())
                .saveMemberOrLogin(authorizationCode, request);
        MemberAuthResponseDTO memberAuthResponseDTO = jwtService.issueToken(vo, response);
        return ResponseEntity.ok().body(ApiResponse.success(AuthSucessType.LOGIN_SUCCESS, memberAuthResponseDTO));
    }
}