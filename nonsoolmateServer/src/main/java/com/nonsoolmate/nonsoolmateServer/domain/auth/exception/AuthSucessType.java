package com.nonsoolmate.nonsoolmateServer.domain.auth.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessSucessType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AuthSucessType implements BusinessSucessType {
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공하였습니다.");

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
