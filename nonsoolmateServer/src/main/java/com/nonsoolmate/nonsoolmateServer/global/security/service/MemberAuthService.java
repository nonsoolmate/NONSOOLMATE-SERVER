package com.nonsoolmate.nonsoolmateServer.global.security.service;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.member.repository.MemberRepository;

import com.nonsoolmate.nonsoolmateServer.global.security.CustomAuthUser;
import com.nonsoolmate.nonsoolmateServer.global.jwt.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberAuthService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberIdOrElseThrowException(Long.parseLong(memberId));

        String password = PasswordUtil.generateRandomPassword();

        return new CustomAuthUser(member, member.getRole());
    }
}
