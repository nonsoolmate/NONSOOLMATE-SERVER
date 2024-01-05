package com.nonsoolmate.nonsoolmateServer.global.error.exception;

import org.springframework.http.HttpStatus;

public interface BusinessExceptionType {
    HttpStatus status();

    String message();

    default int getHttpStatusCode() {
        return status().value();
    }
}
