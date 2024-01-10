package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.repository;


import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.entity.SelectUniversity;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.University;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SelectUniversityRepository extends JpaRepository<SelectUniversity, Long> {

    List<SelectUniversity> findAllByMember(Member member);

    @Transactional
    void deleteByMemberAndUniversity(Member member, University university);

}
