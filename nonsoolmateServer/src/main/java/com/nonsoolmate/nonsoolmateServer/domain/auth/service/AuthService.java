package com.nonsoolmate.nonsoolmateServer.domain.auth.service;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.enums.PlatformType;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.enums.Role;
import com.nonsoolmate.nonsoolmateServer.domain.member.repository.MemberRepository;
import com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.request.MemberRequestDTO;
import com.nonsoolmate.nonsoolmateServer.domain.auth.service.vo.MemberSignUpVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public abstract class AuthService {
    private final MemberRepository memberRepository;

    @Transactional
    public abstract MemberSignUpVO saveMemberOrLogin(String platformType, MemberRequestDTO request);

    protected Member getMember(PlatformType platformType, String email) {
        return memberRepository.findByPlatformTypeAndEmail(platformType, email)
                .orElse(null);
    }

    protected Member saveUser(MemberRequestDTO request, String email, String name, String birthday, String gender,
                              String phoneNumber) {
        Member newMember = createSocialMember(email, name, PlatformType.of(request.platformType()), birthday, gender, phoneNumber);
        return memberRepository.saveAndFlush(newMember);
    }

    private static Member createSocialMember(String email, String name, PlatformType platformType, String birthYear,
                                             String gender, String phoneNumber) {
        return Member.builder().email(email).name(name).platformType(platformType).role(Role.USER)
                .birthYear(birthYear)
                .gender(gender).phoneNumber(phoneNumber).build();
    }
}
