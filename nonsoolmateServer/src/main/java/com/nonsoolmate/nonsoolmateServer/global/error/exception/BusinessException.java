package com.nonsoolmate.nonsoolmateServer.global.error.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final BusinessExceptionType exceptionType;

    public BusinessException(BusinessExceptionType exceptionType) {
        super(exceptionType.message());
        this.exceptionType = exceptionType;
    }

    public int getHttpStatus() {
        return exceptionType.status().value();
    }

}