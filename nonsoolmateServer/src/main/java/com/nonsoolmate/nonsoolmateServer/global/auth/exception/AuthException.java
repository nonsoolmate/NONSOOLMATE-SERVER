package com.nonsoolmate.nonsoolmateServer.global.auth.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessException;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;

public class AuthException extends BusinessException {
    public AuthException(BusinessExceptionType exceptionType) {
        super(exceptionType);
    }
}
