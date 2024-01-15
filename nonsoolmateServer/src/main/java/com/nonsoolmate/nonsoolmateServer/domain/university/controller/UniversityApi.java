package com.nonsoolmate.nonsoolmateServer.domain.university.controller;

import com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.response.UniversityExamImageResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.response.UniversityExamInfoResponseDTO;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "UniversityExam", description = "대학 시험 정보와 관련된 API")
public interface UniversityApi {

    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", content = @Content)
            }
    )

    @Operation(summary = "시험 보기: 시험 이름 & 제한 시간", description = "시험 응시 화면의 이름 및 제한 시간을 조회합니다.")
    ResponseEntity<ApiResponse<UniversityExamInfoResponseDTO>> getUniversityExam(
            @Parameter(description = "해당 대학교 시험 Id (examId)", required = true) Long universityExamId);

    @Operation(summary = "시험 보기: 문제지 [페이지네이션]", description = "시험 응시 화면의 문제지를 조회합니다.")
    ResponseEntity<ApiResponse<Page<UniversityExamImageResponseDTO>>> getUniversityExamImages(
            @Parameter(description = "해당 대학교 시험 Id (examId)", required = true) Long id,
            @RequestParam(defaultValue = "0") int page,
            Pageable pageable);
}
