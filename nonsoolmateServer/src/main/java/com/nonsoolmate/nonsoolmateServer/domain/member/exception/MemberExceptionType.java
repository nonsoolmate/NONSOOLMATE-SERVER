package com.nonsoolmate.nonsoolmateServer.domain.member.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MemberExceptionType implements BusinessExceptionType {

    /**
     * 404 Not Found
     */
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다.");

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
