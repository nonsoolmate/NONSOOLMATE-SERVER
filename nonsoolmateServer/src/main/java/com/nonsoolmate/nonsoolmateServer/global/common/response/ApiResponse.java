package com.nonsoolmate.nonsoolmateServer.global.common.response;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.ErrorType;
import com.nonsoolmate.nonsoolmateServer.global.success.Success;
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

    public static ApiResponse success(Success success) {
        return new ApiResponse<>(success.getHttpStatus(), success.getMessage());
    }

    public static <T> ApiResponse<T> success(Success success, T data) {
        return new ApiResponse<T>(success.getHttpStatus(), success.getMessage(), data);
    }

    public static ApiResponse error(ErrorType error) {
        return new ApiResponse<>(error.getHttpStatusCode(), error.getMessage());
    }

    public static <T> ApiResponse<T> error(ErrorType error, T data) {
        return new ApiResponse<T>(error.getHttpStatusCode(), error.getMessage(), data);
    }
}