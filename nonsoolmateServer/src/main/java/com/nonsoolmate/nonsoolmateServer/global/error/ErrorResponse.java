package com.nonsoolmate.nonsoolmateServer.global.error;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.CommonErrorType;

public record ErrorResponse(int code, String message) {
    public static ErrorResponse of(CommonErrorType errorType) {
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