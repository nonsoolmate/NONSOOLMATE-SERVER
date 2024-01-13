package com.nonsoolmate.nonsoolmateServer.domain.university.repository;

import static com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.exception.SelectUniversityExceptionType.INVALID_SELECTED_UNIVERSITY;

import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.exception.SelectUniversityException;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.University;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findAll();

    Optional<University> findByUniversityId(Long universityId);

    List<University> findAllByOrderByUniversityNameAscUniversityCollegeAsc();

    default University findByUniversityIdOrElseThrowException(Long universityId){
        return findByUniversityId(universityId).orElseThrow(
                ()-> new SelectUniversityException(INVALID_SELECTED_UNIVERSITY));
    }
}
