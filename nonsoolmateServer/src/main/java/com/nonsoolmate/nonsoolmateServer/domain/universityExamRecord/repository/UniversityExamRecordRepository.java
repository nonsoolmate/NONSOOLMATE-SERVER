package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.repository;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.UniversityExam;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.entity.UniversityExamRecord;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityExamRecordRepository extends JpaRepository<UniversityExamRecord, Long> {
    Optional<UniversityExamRecord> findByUniversityExamAndMember(UniversityExam university, Member member);
}
