package com.nonsoolmate.nonsoolmateServer.global.auth.controller;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.global.auth.controller.dto.request.MemberRequestDTO;
import com.nonsoolmate.nonsoolmateServer.global.auth.controller.dto.response.MemberAuthResponseDTO;
import com.nonsoolmate.nonsoolmateServer.global.auth.exception.AuthSucessType;
import com.nonsoolmate.nonsoolmateServer.global.auth.jwt.service.JwtService;
import com.nonsoolmate.nonsoolmateServer.global.auth.service.AuthServiceProvider;
import com.nonsoolmate.nonsoolmateServer.global.auth.service.vo.MemberSignUpVO;
import com.nonsoolmate.nonsoolmateServer.global.common.response.ApiResponse;
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
    public ApiResponse<MemberAuthResponseDTO> login(
            @RequestHeader(value = "authorization-code") final String authorizationCode,
            @RequestBody @Valid final
            MemberRequestDTO request, HttpServletResponse response) {
        MemberSignUpVO vo = authServiceProvider.getAuthService(request.platformType())
                .saveMemberOrLogin(authorizationCode, request);
        jwtService.issueToken(vo, response);
        return ApiResponse.success(AuthSucessType.LOGIN_SUCCESS,
                MemberAuthResponseDTO.of(vo.memberId(), vo.authType(), vo.name()));
    }


}
