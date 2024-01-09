package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.repository;


import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.entity.SelectUniversity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectUniversityRepository extends JpaRepository<SelectUniversity, Long> {

    List<SelectUniversity> findAllByMember(Member member);

}
