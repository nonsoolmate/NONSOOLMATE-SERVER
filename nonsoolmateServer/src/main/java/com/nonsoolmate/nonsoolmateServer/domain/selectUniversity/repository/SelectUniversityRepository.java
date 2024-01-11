package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.repository;


import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.entity.SelectUniversity;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.University;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SelectUniversityRepository extends JpaRepository<SelectUniversity, Long> {

    List<SelectUniversity> findAllByMember(Member member);

    @Query("select su from SelectUniversity su where su.member =:member order by su.university.universityName asc, su.university.universityCollege asc")
    List<SelectUniversity> findAllByMemberOrderByUniversityNameASCUniversityCollegeAsc(Member member);

    @Transactional
    void deleteByMemberAndUniversity(Member member, University university);

}
