package com.nonsoolmate.nonsoolmateServer.domain.auth.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessSucessType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AuthSuccessType implements BusinessSucessType {
    SIGN_UP_SUCCESS(HttpStatus.CREATED, "회원가입에 성공하였습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공하였습니다."),
    REISSUE_SUCCESS(HttpStatus.OK, "리프레시 토큰 재발급에 성공하였습니다.");

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
