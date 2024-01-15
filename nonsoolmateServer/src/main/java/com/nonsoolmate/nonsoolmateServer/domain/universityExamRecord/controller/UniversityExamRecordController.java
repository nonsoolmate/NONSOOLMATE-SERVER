package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller;

import static com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.exception.UniversityExamRecordSuccessType.GET_UNIVERSITY_EXAM_RECORD_RESULT_SUCCESS;
import static com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.exception.UniversityExamRecordSuccessType.GET_UNIVERSITY_EXAM_RECORD_SUCCESS;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response.UniversityExamSheetPreSignedUrlResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response.UniversityExamRecordResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response.UniversityExamRecordResultResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response.UniversityExamRecordIdResponse;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.request.CreateUniversityExamRequestDTO;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.exception.UniversityExamRecordSuccessType;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.service.UniversityExamRecordService;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.service.UniversityExamRecordSheetService;
import com.nonsoolmate.nonsoolmateServer.external.aws.service.vo.PreSignedUrlVO;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.security.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/university/exam-record")
@RequiredArgsConstructor
@Tag(name = "UniversityExamRecord", description = "시험 응시 기록과 관련된 API")
public class UniversityExamRecordController {

    private final UniversityExamRecordService universityExamRecordService;
    private final UniversityExamRecordSheetService universityExamRecordSheetService;


    @Operation(summary = "첨삭: 첨삭 PDF_해제PDF", description = "첨삭 pdf 및 해제 pdf를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UniversityExamRecordResponseDTO>> getUniversityExamRecord(
            @PathVariable("id") Long universityExamId, @AuthUser Member member) {
        return ResponseEntity.ok().body(ApiResponse.success(GET_UNIVERSITY_EXAM_RECORD_SUCCESS,
                universityExamRecordService.getUniversityExamRecord(universityExamId, member)));
    }

    @Operation(summary = "첨삭: 첨삭 PDF 저장", description = "첨삭 pdf를 조회합니다.")
    @GetMapping("/result/{id}")
    public ResponseEntity<ApiResponse<UniversityExamRecordResultResponseDTO>> getUniversityExamRecordResult(
            @PathVariable("id") Long universityExamId, @AuthUser Member member) {
        return ResponseEntity.ok().body(ApiResponse.success(GET_UNIVERSITY_EXAM_RECORD_RESULT_SUCCESS,
                universityExamRecordService.getUniversityExamRecordResult(universityExamId, member)));
    }

    @Operation(summary = "시험 보기: [1] 답안지 업로드 PresignedUrl 조회 API", description = "답안지(시험응시기록) 업로드를 위한 PresignedUrl를 조회합니다.")
    @GetMapping("/sheet/presigned")
    public ResponseEntity<ApiResponse<UniversityExamSheetPreSignedUrlResponseDTO>> getUniversityExamSheetPreSignedUrl() {
        PreSignedUrlVO universityExamRecordSheetPreSignedUrlVO = universityExamRecordSheetService.getUniversityExamRecordSheetPreSignedUrl();
        return ResponseEntity.ok().body(ApiResponse.success(
                UniversityExamRecordSuccessType.GET_UNIVERSITY_EXAM_RECORD_SHEET_PRESIGNED_SUCCESS,
                UniversityExamSheetPreSignedUrlResponseDTO.of(universityExamRecordSheetPreSignedUrlVO.getFileName(),
                        universityExamRecordSheetPreSignedUrlVO.getUrl())));
    }

    @Operation(summary = "시험보기: [3] 답안지 업로드 후 시험 기록 API", description = "답안지(시험응시기록) 업로드 후 서버에 기록하기 위해 호출합니다.")
    @PostMapping("/sheet")
    public ResponseEntity<ApiResponse<UniversityExamRecordIdResponse>> createUniversityExamRecord(
            @Valid @RequestBody CreateUniversityExamRequestDTO createUniversityExamRequestDTO,
            @AuthUser Member member) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(
                UniversityExamRecordSuccessType.CREATE_UNIVERSITY_EXAM_RECORD_SUCCESS,
                universityExamRecordService.createUniversityExamRecord(
                        createUniversityExamRequestDTO, member)));
    }
}
