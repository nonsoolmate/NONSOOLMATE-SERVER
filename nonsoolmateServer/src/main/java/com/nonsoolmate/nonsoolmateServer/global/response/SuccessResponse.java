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
public class SuccessResponse<T> {
    private final int code;
    private final String message;
    private T data;

    public static SuccessResponse of(SuccessType successType) {
        return new SuccessResponse<>(successType.getHttpStatusCode(), successType.message());
    }

    public static <T> SuccessResponse<T> of(SuccessType successType, T data) {
        return new SuccessResponse<T>(successType.getHttpStatusCode(), successType.message(), data);
    }
}