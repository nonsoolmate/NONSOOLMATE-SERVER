package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessSucessType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum SelectUniversitySuccessType implements BusinessSucessType {
    GET_SELECT_UNIVERSITIES_SUCCESS(HttpStatus.OK, "목표 대학 조회에 성공하였습니다."),
    GET_SELECT_UNIVERSITY_EXAMS_SUCCESS(HttpStatus.OK, "목표 대학 시험 리스트 조회에 성공하였습니다.");

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
