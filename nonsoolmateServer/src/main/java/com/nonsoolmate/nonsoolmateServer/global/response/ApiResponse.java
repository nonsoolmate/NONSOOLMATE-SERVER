package com.nonsoolmate.nonsoolmateServer.global.response;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.ExceptionType;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.SuccessType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ApiResponse<T> {
    private final int code;
    private final String message;
    private T data;

    public static ApiResponse success(SuccessType successType) {
        return new ApiResponse<>(successType.getHttpStatusCode(), successType.message());
    }

    public static <T> ApiResponse<T> success(SuccessType successType, T data) {
        return new ApiResponse<T>(successType.getHttpStatusCode(), successType.message(), data);
    }

    public static ApiResponse error(ExceptionType exceptionType) {
        return new ApiResponse<>(exceptionType.getHttpStatusCode(), exceptionType.message());
    }

    public static <T> ApiResponse<T> error(ExceptionType exceptionType, T data) {
        return new ApiResponse<T>(exceptionType.getHttpStatusCode(), exceptionType.message(), data);
    }
}