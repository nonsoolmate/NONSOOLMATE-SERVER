package com.nonsoolmate.nonsoolmateServer.domain.member.repository;


import static com.nonsoolmate.nonsoolmateServer.domain.member.exception.MemberExceptionType.NOT_FOUND_MEMBER;

import com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthException;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.enums.PlatformType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByPlatformTypeAndEmail(PlatformType platformType, String email);

    Optional<Member> findByMemberId(Long memberId);

    default Member findByMemberIdOrElseThrowException(Long memberId){
        return findByMemberId(memberId).orElseThrow(
                () -> new AuthException(NOT_FOUND_MEMBER));
    }
}
