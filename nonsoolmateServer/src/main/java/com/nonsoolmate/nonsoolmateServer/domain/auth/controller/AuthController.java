package com.nonsoolmate.nonsoolmateServer.domain.auth.controller;

import com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.request.MemberRequestDTO;
import com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.response.MemberAuthResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.response.MemberReissueResponseDTO;
import com.nonsoolmate.nonsoolmateServer.global.jwt.service.JwtService;
import com.nonsoolmate.nonsoolmateServer.domain.auth.service.AuthServiceProvider;
import com.nonsoolmate.nonsoolmateServer.domain.auth.service.vo.MemberSignUpVO;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthSuccessType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Value("${spring.security.oauth2.client.naver.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.naver.redirect-uri}")
    private String redirectUri;

    @PostMapping("/social/login")
    public ResponseEntity<ApiResponse<MemberAuthResponseDTO>> login(
            @RequestHeader(value = "authorization-code") final String authorizationCode,
            @RequestBody @Valid final
            MemberRequestDTO request, HttpServletResponse response) {
        MemberSignUpVO vo = authServiceProvider.getAuthService(request.platformType())
                .saveMemberOrLogin(authorizationCode, request);
        MemberAuthResponseDTO memberAuthResponseDTO = jwtService.issueToken(vo, response);
        return ResponseEntity.ok().body(ApiResponse.success(AuthSuccessType.LOGIN_SUCCESS, memberAuthResponseDTO));
    }

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse<MemberReissueResponseDTO>> reissue(HttpServletRequest request,
                                                                         HttpServletResponse response) {
        MemberReissueResponseDTO memberReissueResponseDTO = jwtService.reissueToken(request, response);
        return ResponseEntity.ok().body(ApiResponse.success(AuthSuccessType.REISSUE_SUCCESS, memberReissueResponseDTO));
    }

    @GetMapping("/authTest")
    public String authTest(HttpServletRequest request, HttpServletResponse response) {
        String redirectURL = "https://nid.naver.com/oauth2.0/authorize?client_id=" + clientId
                + "&redirect_uri=" + redirectUri + "&response_type=code";
        try {
            response.sendRedirect(
                    redirectURL);
        } catch (Exception e) {
            log.info("authTest = {}", e);
        }

        return "SUCCESS";
    }
}
