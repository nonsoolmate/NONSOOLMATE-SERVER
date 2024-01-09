package com.nonsoolmate.nonsoolmateServer.domain.university.controller;

import com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.UniversityExamInfoResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.university.service.UniversityExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/university/exam")
@RequiredArgsConstructor
public class UniversityExamController {
    private final UniversityExamService universityExamService;

    @GetMapping("/info/{id}")
    public UniversityExamInfoResponseDTO getUniversityExam(@PathVariable("id") Long universityExamId) {
        return universityExamService.getUniversityExam(universityExamId);
    }
}
