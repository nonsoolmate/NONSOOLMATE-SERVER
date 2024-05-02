package com.nonsoolmate.nonsoolmateServer.domain.university.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.ClientException;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.ExceptionType;

public class UniversityExamException extends ClientException {
    public UniversityExamException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
