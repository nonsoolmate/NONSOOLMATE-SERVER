package com.nonsoolmate.nonsoolmateServer.domain.member.controller;

import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.NameResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.TicketResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.member.exception.MemberSuccessType;
import com.nonsoolmate.nonsoolmateServer.domain.member.service.MemberService;
import com.nonsoolmate.nonsoolmateServer.global.response.SuccessResponse;
import com.nonsoolmate.nonsoolmateServer.global.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyController implements MemberApi{
    private final MemberService memberService;

    @Override
    @GetMapping("/name")
    public ResponseEntity<SuccessResponse<NameResponseDTO>> getName(@AuthUser Member member) {
        return ResponseEntity.ok()
                .body(SuccessResponse.of(MemberSuccessType.GET_MEMBER_NAME_SUCCESS,
                        memberService.getNickname(member)));
    }

    @Override
    @GetMapping("/ticket")
    public ResponseEntity<SuccessResponse<TicketResponseDTO>> getTicket(@AuthUser Member member) {
        return ResponseEntity.ok()
                .body(SuccessResponse.of(MemberSuccessType.GET_MEMBER_TICKET_SUCCESS,
                        memberService.getTicket(member)));
    }
}
