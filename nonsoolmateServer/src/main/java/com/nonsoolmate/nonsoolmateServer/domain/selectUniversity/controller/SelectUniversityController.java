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
@Tag(name = "SelectUniversity", description = "목표 대학과 관련된 API")
public class SelectUniversityController {

    private final SelectUniversityService selectUniversityService;

    @Operation(summary = "목표대학 설정: 리스트 조회", description = "내 목표 대학 리스트를 조회합니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<SelectUniversityResponseDTO>>> getSelectUniversities(
            @AuthUser Member member) {

        return ResponseEntity.ok().body(ApiResponse.success(GET_SELECT_UNIVERSITIES_SUCCESS,
                selectUniversityService.getSelectUniversities(member)));
    }

    @Operation(summary = "마이 페이지: 대학별 시험 리스트 조회", description = "내 목표 대학들의 시험 리스트를 조회합니다.")
    @GetMapping("/exam")
    public ResponseEntity<ApiResponse<List<SelectUniversityExamsResponseDTO>>> getSelectUniversityExams(
            @AuthUser Member member) {
        return ResponseEntity.ok().body(ApiResponse.success(GET_SELECT_UNIVERSITY_EXAMS_SUCCESS,
                selectUniversityService.getSelectUniversityExams(member)));
    }

    @Operation(summary = "목표대학 설정: 리스트 선택", description = "내 목표 대학들 리스트를 업데이트(수정) 합니다.")
    @PatchMapping
    public ResponseEntity<ApiResponse<SelectUniversityUpdateResponseDTO>> patchSelectUniversities(
            @AuthUser Member member,
            @RequestBody @Valid final List<SelectUniversityRequestDTO> request) {

        return ResponseEntity.ok().body(ApiResponse.success(PATCH_SELECT_UNIVERSITIES_SUCCESS,
                selectUniversityService.patchSelectUniversities(member, request)));
    }
}
