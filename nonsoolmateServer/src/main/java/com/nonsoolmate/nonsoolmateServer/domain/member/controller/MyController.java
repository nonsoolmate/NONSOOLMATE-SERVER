package com.nonsoolmate.nonsoolmateServer.domain.member.controller;

<<<<<<< HEAD
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.NameResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.TicketResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
=======
<<<<<<< HEAD
<<<<<<< HEAD
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.NameResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.TicketResponseDTO;
=======
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.nicknameResponseDTO;
>>>>>>> a7a4415 ([FEAT] MyController 개발)
=======
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.NameResponseDTO;
>>>>>>> 0660a64 ([REFACTOR] nickname -> name 네이밍 리팩터링)
>>>>>>> 726184a ([REFACTOR] nickname -> name 네이밍 리팩터링)
import com.nonsoolmate.nonsoolmateServer.domain.member.exception.MemberSuccessType;
import com.nonsoolmate.nonsoolmateServer.domain.member.service.MemberService;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.security.AuthUser;
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
    public ResponseEntity<ApiResponse<NameResponseDTO>> getName(@AuthUser Member member) {
        return ResponseEntity.ok()
                .body(ApiResponse.success(MemberSuccessType.GET_MEMBER_NAME_SUCCESS,
                        memberService.getNickname(member)));
    }

    @GetMapping("/ticket")
    public ResponseEntity<ApiResponse<TicketResponseDTO>> getTicket(@AuthUser Member member) {
        return ResponseEntity.ok()
                .body(ApiResponse.success(MemberSuccessType.GET_MEMBER_TICKET_SUCCESS,
                        memberService.getTicket(member)));
    }
}
