package com.nonsoolmate.nonsoolmateServer.global.error.exception;

public class BusinessException extends RuntimeException {
    private final ExceptionType exceptionType;

    public BusinessException(ExceptionType exceptionType) {
        super(exceptionType.message());
        this.exceptionType = exceptionType;
    }
}
