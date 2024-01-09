package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller;

import static com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.exception.SelectUniversitySuccessType.GET_SELECT_UNIVERSITIES_SUCCESS;
import static com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.exception.SelectUniversitySuccessType.GET_SELECT_UNIVERSITY_EXAMS_SUCCESS;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityExamsResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.service.SelectUniversityService;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.security.AuthUser;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/select-university")
public class SelectUniversityController {

    private final SelectUniversityService selectUniversityService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SelectUniversityResponseDTO>>> getSelectUniversities(
            @AuthUser Member member) {

        return ResponseEntity.ok().body(ApiResponse.success(GET_SELECT_UNIVERSITIES_SUCCESS,
                selectUniversityService.getSelectUniversities(member)));
    }

    @GetMapping("/exam")
    public ResponseEntity<ApiResponse<List<SelectUniversityExamsResponseDTO>>> getSelectUniversityExams(
            @AuthUser Member member) {
        return ResponseEntity.ok().body(ApiResponse.success(GET_SELECT_UNIVERSITY_EXAMS_SUCCESS,
                selectUniversityService.getSelectUniversityExams(member)));
    }
}
