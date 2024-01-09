package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)

public enum SelectUniversityExceptionType implements BusinessExceptionType {
    ;

    @Override
    public HttpStatus status() {
        return null;
    }

    @Override
    public String message() {
        return null;
    }
}
