package com.nonsoolmate.nonsoolmateServer.domain.member.service;

<<<<<<< HEAD
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.NameResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.TicketResponseDTO;
=======
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.nicknameResponseDTO;
>>>>>>> 6409e08 ([FEAT] MemberService 내 getName() 구현)
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.member.repository.MemberRepository;
import com.nonsoolmate.nonsoolmateServer.global.security.CustomAuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public NameResponseDTO getNickname(Member member) {
        return NameResponseDTO.of(member.getName());
    }

    public TicketResponseDTO getTicket(Member member) {
        return TicketResponseDTO.of(member.getName(), member.getTicketCount());
    }
}
