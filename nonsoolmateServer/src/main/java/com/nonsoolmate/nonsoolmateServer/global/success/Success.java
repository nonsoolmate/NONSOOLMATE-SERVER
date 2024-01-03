package com.nonsoolmate.nonsoolmateServer.global.success;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Success {

    // 200 OK
    GET_SERVER_PROFILE(HttpStatus.OK, "서버 프로필 확인 완료");

    // 201 created


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatus(){
        return httpStatus.value();
    }

    public String getMessage(){
        return message;
    }
}