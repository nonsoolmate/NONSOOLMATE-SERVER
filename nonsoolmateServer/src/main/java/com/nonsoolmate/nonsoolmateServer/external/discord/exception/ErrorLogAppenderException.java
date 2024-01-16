package com.nonsoolmate.nonsoolmateServer.external.discord.exception;


import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessException;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;

public class ErrorLogAppenderException extends BusinessException {
    public ErrorLogAppenderException(BusinessExceptionType exceptionType) {
        super(exceptionType);
    }
}
