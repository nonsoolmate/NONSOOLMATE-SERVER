package com.nonsoolmate.nonsoolmateServer.domain.auth.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthExceptionType implements BusinessExceptionType {

    /**
     * 400 Bad Request
     */
    INVALID_MEMBER_PLATFORM_AUTHORIZATION_CODE(HttpStatus.BAD_REQUEST, "유효하지 않은 플랫폼 인가코드입니다."),
    INVALID_PLATFORM_TYPE(HttpStatus.BAD_REQUEST, "올바르지 않은 플랫폼 유형입니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "서비스에서 발급되지 않은 액세스 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "서비스에서 발급되지 않은 리프레시 토큰입니다."),

    /**
     * 401 Unauthorized
     */
    UNAUTHORIZED_MEMBER_LOGIN(HttpStatus.UNAUTHORIZED, "로그인에 실패하였습니다."),
    UNAUTHORIZED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "기한이 만료된 액세스 토큰입니다."),

    /**
     * 404 Not Found
     */

    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "유효한 멤버를 찾지 못했습니다."),
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.NOT_FOUND, "유효한 리프레시 토큰을 찾지 못했습니다.");

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
