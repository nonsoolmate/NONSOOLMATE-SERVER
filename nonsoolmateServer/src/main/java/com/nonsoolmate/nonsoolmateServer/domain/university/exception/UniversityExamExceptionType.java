package com.nonsoolmate.nonsoolmateServer.domain.university.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.ExceptionType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UniversityExamExceptionType implements ExceptionType {

    INVALID_UNIVERSITY_EXAM(HttpStatus.BAD_REQUEST, "존재하지 않는 대학 시험입니다."),
    NOT_FOUND_UNIVERSITY_EXAM_IMAGE(HttpStatus.NOT_FOUND, "존재하지 않는 대학 시험 입니다.");

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
