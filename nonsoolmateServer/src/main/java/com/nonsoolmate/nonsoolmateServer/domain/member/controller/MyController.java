package com.nonsoolmate.nonsoolmateServer.domain.member.controller;

import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.NameResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.TicketResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.member.exception.MemberSuccessType;
import com.nonsoolmate.nonsoolmateServer.domain.member.service.MemberService;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.security.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
@Tag(name = "my", description = "멤버와 관련된 API")
public class MyController {
    private final MemberService memberService;

    @Operation(summary = "마이페이지: 이름", description = "내 이름을 조회합니다.")
    @GetMapping("/name")
    public ResponseEntity<ApiResponse<NameResponseDTO>> getName(@AuthUser Member member) {
        return ResponseEntity.ok()
                .body(ApiResponse.success(MemberSuccessType.GET_MEMBER_NAME_SUCCESS,
                        memberService.getNickname(member)));
    }

    @Operation(summary = "내 정보 확인: 첨삭권 개수", description = "내 첨삭권 갯수를 조회합니다.")
    @GetMapping("/ticket")
    public ResponseEntity<ApiResponse<TicketResponseDTO>> getTicket(@AuthUser Member member) {
        return ResponseEntity.ok()
                .body(ApiResponse.success(MemberSuccessType.GET_MEMBER_TICKET_SUCCESS,
                        memberService.getTicket(member)));
    }
}
