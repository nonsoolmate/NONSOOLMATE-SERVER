package com.nonsoolmate.nonsoolmateServer.global.error;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final Error error;
    public BusinessException(Error error,String message) {
        super(message);
        this.error = error;
    }
    public int getHttpStatus() {
        return error.getHttpStatus();
    }
}