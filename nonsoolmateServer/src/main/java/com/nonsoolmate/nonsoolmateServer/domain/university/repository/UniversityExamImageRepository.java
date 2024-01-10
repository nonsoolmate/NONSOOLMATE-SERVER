package com.nonsoolmate.nonsoolmateServer.domain.university.repository;

import com.nonsoolmate.nonsoolmateServer.domain.university.entity.UniversityExam;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.UniversityExamImage;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityExamImageRepository extends JpaRepository<UniversityExamImage, Long> {
    Page<UniversityExamImage> findAllByUniversityExam(UniversityExam universityExam, Pageable pageable);

    List<UniversityExamImage> findAllByUniversityExam(UniversityExam universityExam);

}
