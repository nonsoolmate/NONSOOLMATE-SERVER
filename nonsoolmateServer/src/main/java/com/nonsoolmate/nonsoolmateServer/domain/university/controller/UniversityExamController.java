package com.nonsoolmate.nonsoolmateServer.domain.university.controller;

import static com.nonsoolmate.nonsoolmateServer.domain.university.exception.UniversityExamSuccessType.GET_UNIVERSITY_EXAM_IMAGE_AND_ANSWER_SUCCESS;

import com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.response.UniversityExamImageAndAnswerResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.response.UniversityExamImageResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.response.UniversityExamInfoResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.university.exception.UniversityExamSuccessType;
import com.nonsoolmate.nonsoolmateServer.domain.university.service.UniversityExamService;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/university/exam")
@RequiredArgsConstructor
@Tag(name = "UniversityExam", description = "대학 시험 정보와 관련된 API")
public class UniversityExamController {
    private final UniversityExamService universityExamService;

    @Operation(summary = "시험 보기: 시험 이름 & 제한 시간", description = "시험 응시 화면의 이름 및 제한 시간을 조회합니다.")
    @GetMapping("/{id}/info")
    public ResponseEntity<ApiResponse<UniversityExamInfoResponseDTO>> getUniversityExam(
            @PathVariable("id") Long universityExamId) {
        return ResponseEntity.ok().body(ApiResponse.success(UniversityExamSuccessType.GET_UNIVERSITY_EXAM_SUCCESS,
                universityExamService.getUniversityExam(universityExamId)));
    }

    @Operation(summary = "시험 보기: 문제지 [페이지네이션]", description = "시험 응시 화면의 문제지를 조회합니다.")
    @GetMapping("{id}/image")
    public ResponseEntity<ApiResponse<Page<UniversityExamImageResponseDTO>>> getUniversityExamImages(
            @PathVariable("id") Long id, @RequestParam(defaultValue = "0") int page, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(page, 1);
        Page<UniversityExamImageResponseDTO> images = universityExamService.getUniversityExamImages(id, pageRequest);
        return ResponseEntity.ok()
                .body(ApiResponse.success(UniversityExamSuccessType.GET_UNIVERSITY_EXAM_IMAGE_SUCCESS, images));
    }

    @Operation(summary = "해제: 문제이미지_해제PDF", description = "문제 이미지 및 해제 pdf를 조회합니다.")
    @GetMapping("{id}/answer")
    public ResponseEntity<ApiResponse<UniversityExamImageAndAnswerResponseDTO>> getUniversityExamImageAndAnswer(
            @PathVariable("id") Long universityExamId
    ) {
        return ResponseEntity.ok().body(ApiResponse.success(GET_UNIVERSITY_EXAM_IMAGE_AND_ANSWER_SUCCESS,
                universityExamService.getUniversityExamImageAndAnswer(universityExamId)));
    }
}
