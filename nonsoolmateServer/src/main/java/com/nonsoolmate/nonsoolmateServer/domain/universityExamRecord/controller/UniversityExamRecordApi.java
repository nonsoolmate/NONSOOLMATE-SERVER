package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.request.CreateUniversityExamRequestDTO;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response.UniversityExamRecordIdResponse;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response.UniversityExamRecordResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response.UniversityExamRecordResultResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response.UniversityExamSheetPreSignedUrlResponseDTO;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.security.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "UniversityExamRecord", description = "시험 응시 기록과 관련된 API")
public interface UniversityExamRecordApi {

    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", content = @Content)
            }
    )

    @Operation(summary = "첨삭: 첨삭 PDF_해제PDF", description = "첨삭 pdf 및 해제 pdf를 조회합니다.")
    ResponseEntity<ApiResponse<UniversityExamRecordResponseDTO>> getUniversityExamRecord(
            @Parameter(description = "해당 대학교 시험 Id (examId)", required = true) @PathVariable("id") Long universityExamId,
            @AuthUser Member member);

    @Operation(summary = "첨삭: 첨삭 PDF 저장", description = "첨삭 pdf를 조회합니다.")
    ResponseEntity<ApiResponse<UniversityExamRecordResultResponseDTO>> getUniversityExamRecordResult(
            @Parameter(description = "해당 대학교 시험 Id (examId)", required = true) @PathVariable("id") Long universityExamId,
            @AuthUser Member member);

    @Operation(summary = "시험 보기: [1] 답안지 업로드 PresignedUrl 조회 API", description = "답안지(시험응시기록) 업로드를 위한 PresignedUrl를 조회합니다.")
    ResponseEntity<ApiResponse<UniversityExamSheetPreSignedUrlResponseDTO>> getUniversityExamSheetPreSignedUrl();

    @Operation(summary = "시험보기: [3] 답안지 업로드 후 시험 기록 API", description = "답안지(시험응시기록) 업로드 후 서버에 기록하기 위해 호출합니다.")
    ResponseEntity<ApiResponse<UniversityExamRecordIdResponse>> createUniversityExamRecord(
            @Parameter(description = "해당 대학교 시험 Id (examId), 시험 응시 소요 시간, 답안지(응시 기록) 파일 이름", required = true) @Valid @RequestBody CreateUniversityExamRequestDTO createUniversityExamRequestDTO,
            @AuthUser Member member);
}
