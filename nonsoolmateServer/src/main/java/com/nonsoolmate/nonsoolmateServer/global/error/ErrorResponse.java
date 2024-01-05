package com.nonsoolmate.nonsoolmateServer.global.error;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.ErrorType;

public record ErrorResponse(int code, String message) {
    public static ErrorResponse of(ErrorType errorType) {
        return new ErrorResponse(
                errorType.getHttpStatusCode(),
                errorType.getMessage()
        );
    }

    public static ErrorResponse of(BusinessExceptionType exceptionType) {
        return new ErrorResponse(
                exceptionType.getHttpStatusCode(),
                exceptionType.message()
        );
    }
}