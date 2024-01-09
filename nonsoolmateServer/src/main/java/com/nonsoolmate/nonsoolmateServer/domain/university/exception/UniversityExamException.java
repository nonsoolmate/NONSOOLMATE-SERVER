package com.nonsoolmate.nonsoolmateServer.domain.university.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessException;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;

public class UniversityExamException extends BusinessException {
    public UniversityExamException(BusinessExceptionType exceptionType) {
        super(exceptionType);
    }
}
