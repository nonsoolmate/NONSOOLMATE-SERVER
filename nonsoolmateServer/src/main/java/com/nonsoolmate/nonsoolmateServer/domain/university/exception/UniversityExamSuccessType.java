package com.nonsoolmate.nonsoolmateServer.domain.university.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessSucessType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UniversityExamSuccessType implements BusinessSucessType {
    /**
     * 200 OK
     */
    GET_UNIVERSITY_EXAM_SUCCESS(HttpStatus.OK, "대학 조회에 성공했습니다"),
    GET_UNIVERSITY_EXAM_IMAGE_SUCCESS(HttpStatus.OK, "대학 시험 이미지 조회에 성공했습니다"),
    GET_UNIVERSITY_EXAM_IMAGE_AND_ANSWER_SUCCESS(HttpStatus.OK, "대학 시험 이미지 및 해제 PDF 조회에 성공했습니다");


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
