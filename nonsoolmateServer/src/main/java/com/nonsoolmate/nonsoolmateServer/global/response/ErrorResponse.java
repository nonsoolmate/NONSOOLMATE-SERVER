package com.nonsoolmate.nonsoolmateServer.global.response;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.CommonErrorType;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.ExceptionType;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.SuccessType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private final int code;
    private final String message;

    public static ErrorResponse of(ExceptionType exceptionType) {
        return new ErrorResponse(exceptionType.getHttpStatusCode(), exceptionType.message());
    }
    
    public static ErrorResponse of(ExceptionType exceptionType, String addMessage){
        return new ErrorResponse(exceptionType.getHttpStatusCode(), exceptionType.message() +": " + addMessage);
    }
}
