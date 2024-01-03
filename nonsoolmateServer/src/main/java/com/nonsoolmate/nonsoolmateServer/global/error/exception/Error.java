package com.nonsoolmate.nonsoolmateServer.global.error.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Error {

    // 400 BAD REQUEST
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),

    // 404 NOT FOUND

    // 500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatus(){
        return httpStatus.value();
    }

    public String getMessage(){
        return message;
    }

}
