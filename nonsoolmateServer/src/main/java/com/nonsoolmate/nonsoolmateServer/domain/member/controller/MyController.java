package com.nonsoolmate.nonsoolmateServer.domain.member.controller;

import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.nicknameResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.exception.MemberSuccessType;
import com.nonsoolmate.nonsoolmateServer.domain.member.service.MemberService;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyController {
    private final MemberService memberService;

    @GetMapping("/name")
    public ResponseEntity<ApiResponse<nicknameResponseDTO>> getName() {
        return ResponseEntity.ok()
                .body(ApiResponse.success(MemberSuccessType.GET_MEMBER_NAME_SUCCESS, memberService.getNickname()));
    }
}