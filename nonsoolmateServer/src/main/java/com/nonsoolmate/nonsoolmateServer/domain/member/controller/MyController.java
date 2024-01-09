package com.nonsoolmate.nonsoolmateServer.domain.member.controller;

import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.NameResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.TicketResponseDTO;
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
    public ResponseEntity<ApiResponse<NameResponseDTO>> getName() {
        return ResponseEntity.ok()
                .body(ApiResponse.success(MemberSuccessType.GET_MEMBER_NAME_SUCCESS, memberService.getNickname()));
    }

    @GetMapping("/ticket")
    public ResponseEntity<ApiResponse<TicketResponseDTO>> getTicket() {
        return ResponseEntity.ok()
                .body(ApiResponse.success(MemberSuccessType.GET_MEMBER_TICKET_SUCCESS, memberService.getTicket()));
    }
}
