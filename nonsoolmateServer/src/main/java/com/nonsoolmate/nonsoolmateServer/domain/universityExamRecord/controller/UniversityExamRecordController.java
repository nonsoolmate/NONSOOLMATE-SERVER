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
public class UniversityExamRecordController {

    private final UniversityExamRecordService universityExamRecordService;
    private final UniversityExamRecordSheetService universityExamRecordSheetService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UniversityExamRecordResponseDTO>> getUniversityExamRecord(
            @PathVariable("id") Long universityExamId, @AuthUser Member member) {
        return ResponseEntity.ok().body(ApiResponse.success(GET_UNIVERSITY_EXAM_RECORD_SUCCESS,
                universityExamRecordService.getUniversityExamRecord(universityExamId, member)));
    }

    @GetMapping("/result/{id}")
    public ResponseEntity<ApiResponse<UniversityExamRecordResultResponseDTO>> getUniversityExamRecordResult(
            @PathVariable("id") Long universityExamId, @AuthUser Member member) {
        return ResponseEntity.ok().body(ApiResponse.success(GET_UNIVERSITY_EXAM_RECORD_RESULT_SUCCESS,
                universityExamRecordService.getUniversityExamRecordResult(universityExamId, member)));
    }

    @GetMapping("/sheet/presigned")
    public ResponseEntity<ApiResponse<UniversityExamSheetPreSignedUrlResponseDTO>> getUniversityExamSheetPreSignedUrl() {
        PreSignedUrlVO universityExamRecordSheetPreSignedUrlVO = universityExamRecordSheetService.getUniversityExamRecordSheetPreSignedUrl();
        return ResponseEntity.ok().body(ApiResponse.success(
                UniversityExamRecordSuccessType.GET_UNIVERSITY_EXAM_RECORD_SHEET_PRESIGNED_SUCCESS,
                UniversityExamSheetPreSignedUrlResponseDTO.of(universityExamRecordSheetPreSignedUrlVO.getFileName(),
                        universityExamRecordSheetPreSignedUrlVO.getUrl())));
    }

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
