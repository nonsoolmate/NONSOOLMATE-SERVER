package com.nonsoolmate.nonsoolmateServer.domain.university.service;

import com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.UniversityExamInfoResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.UniversityExam;
import com.nonsoolmate.nonsoolmateServer.domain.university.exception.UniversityExamException;
import com.nonsoolmate.nonsoolmateServer.domain.university.exception.UniversityExamExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nonsoolmate.nonsoolmateServer.domain.university.repository.UniversityExamRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UniversityExamService {
    private final UniversityExamRepository universityExamRepository;

    public UniversityExamInfoResponseDTO getUniversityExam(Long universityExamId) {
        UniversityExam universityExam = universityExamRepository.findByUniversityExamId(universityExamId)
                .orElseThrow(() -> new UniversityExamException(
                        UniversityExamExceptionType.NOT_FOUND_UNIVERSITY_EXAM));
        return UniversityExamInfoResponseDTO.of(universityExam.getUniversityExamId(), universityExam.getExamName(),
                universityExam.getExamTimeLimit());
    }

}
