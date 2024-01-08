package com.nonsoolmate.nonsoolmateServer.domain.member.service;

import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.NameResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.member.repository.MemberRepository;
import com.nonsoolmate.nonsoolmateServer.global.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public NameResponseDTO getNickname() {
        Member member = SecurityUtil.getLoginMember();
        return NameResponseDTO.of(member.getName());
    }
}
