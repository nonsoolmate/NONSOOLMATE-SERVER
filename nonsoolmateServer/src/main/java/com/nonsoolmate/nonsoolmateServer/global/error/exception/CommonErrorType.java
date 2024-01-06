package com.nonsoolmate.nonsoolmateServer.global.error.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CommonErrorType implements BusinessExceptionType {
    /**
     * 404 Not Found
     */
    NOT_FOUND_PATH(HttpStatus.NOT_FOUND, "요청하신 경로를 찾을 수 없습니다"),

    /**
     * 500 Internal Server Error
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다"),

    ;

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
