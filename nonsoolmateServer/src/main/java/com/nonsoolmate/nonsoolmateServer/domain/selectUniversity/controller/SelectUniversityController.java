package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller;

import static com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.exception.SelectUniversitySuccessType.GET_SELECT_UNIVERSITIES_SUCCESS;
import static com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.exception.SelectUniversitySuccessType.GET_SELECT_UNIVERSITY_EXAMS_SUCCESS;
import static com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.exception.SelectUniversitySuccessType.PATCH_SELECT_UNIVERSITIES_SUCCESS;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.request.SelectUniversityRequestDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityExamsResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityUpdateResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.service.SelectUniversityService;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.security.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/select-university")
public class SelectUniversityController implements SelectUniversityApi {

    private final SelectUniversityService selectUniversityService;

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<SelectUniversityResponseDTO>>> getSelectUniversities(
            @AuthUser Member member) {

        return ResponseEntity.ok().body(ApiResponse.success(GET_SELECT_UNIVERSITIES_SUCCESS,
                selectUniversityService.getSelectUniversities(member)));
    }

    @Override
    @GetMapping("/exam")
    public ResponseEntity<ApiResponse<List<SelectUniversityExamsResponseDTO>>> getSelectUniversityExams(
            @AuthUser Member member) {
        return ResponseEntity.ok().body(ApiResponse.success(GET_SELECT_UNIVERSITY_EXAMS_SUCCESS,
                selectUniversityService.getSelectUniversityExams(member)));
    }

    @Override
    @PatchMapping
    public ResponseEntity<ApiResponse<SelectUniversityUpdateResponseDTO>> patchSelectUniversities(
            @AuthUser Member member,
            @RequestBody @Valid final List<SelectUniversityRequestDTO> request) {

        return ResponseEntity.ok().body(ApiResponse.success(PATCH_SELECT_UNIVERSITIES_SUCCESS,
                selectUniversityService.patchSelectUniversities(member, request)));
    }
}
