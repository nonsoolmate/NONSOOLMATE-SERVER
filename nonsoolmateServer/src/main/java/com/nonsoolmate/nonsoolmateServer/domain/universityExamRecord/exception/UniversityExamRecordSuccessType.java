package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessSucessType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UniversityExamRecordSuccessType implements BusinessSucessType {
    GET_UNIVERSITY_EXAM_RECORD_SUCCESS(HttpStatus.OK, "첨삭 PDF, 해제 PDF 조회에 성공했습니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus status() {
        return this.status;
    }

    @Override
    public String message() {
        return this.message;
    }
}
