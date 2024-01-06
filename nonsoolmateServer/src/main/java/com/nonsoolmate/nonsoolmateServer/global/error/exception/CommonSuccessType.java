package com.nonsoolmate.nonsoolmateServer.global.error.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Success implements BusinessSucessType {

    // 200 OK
    GET_SERVER_PROFILE(HttpStatus.OK, "서버 프로필 확인 완료");

    // 201 created


    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus status() {
        return null;
    }

    @Override
    public String message() {
        return null;
    }

    @Override
    public int getHttpStatusCode() {
        return BusinessSucessType.super.getHttpStatusCode();
    }
}