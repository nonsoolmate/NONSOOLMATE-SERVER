package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller;

import static com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.exception.UniversityExamRecordSuccessType.GET_UNIVERSITY_EXAM_RECORD_RESULT_SUCCESS;
import static com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.exception.UniversityExamRecordSuccessType.GET_UNIVERSITY_EXAM_RECORD_SUCCESS;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.UniversityExamRecordResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.UniversityExamRecordResultResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.service.UniversityExamRecordService;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/university/exam-record")
@RequiredArgsConstructor
public class UniversityExamRecordController {

    private final UniversityExamRecordService universityExamRecordService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UniversityExamRecordResponseDTO>> getUniversityExamRecord(
            @PathVariable("id") Long universityExamId,
            @AuthUser Member member
    ) {
        return ResponseEntity.ok().body(ApiResponse.success(GET_UNIVERSITY_EXAM_RECORD_SUCCESS,
                universityExamRecordService.getUniversityExamRecord(universityExamId, member)));
    }

    @GetMapping("/result/{id}")
    public ResponseEntity<ApiResponse<UniversityExamRecordResultResponseDTO>> getUniversityExamRecordResult(
            @PathVariable("id") Long universityExamId,
            @AuthUser Member member
    ){
        return ResponseEntity.ok().body(ApiResponse.success(GET_UNIVERSITY_EXAM_RECORD_RESULT_SUCCESS,
                universityExamRecordService.getUniversityExamRecordResult(universityExamId, member)));
    }
}
