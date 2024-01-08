package com.nonsoolmate.nonsoolmateServer.domain.university.repository;

import com.nonsoolmate.nonsoolmateServer.domain.university.entity.UniversityExam;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityExamRepository extends JpaRepository<UniversityExam, Long> {
    Optional<UniversityExam> findByUniversityExamId(Long universityId);
}
