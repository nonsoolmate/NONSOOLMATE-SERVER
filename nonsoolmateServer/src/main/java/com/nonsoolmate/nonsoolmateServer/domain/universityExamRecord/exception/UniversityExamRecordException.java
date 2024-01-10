package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessException;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;

public class UniversityExamRecordException extends BusinessException {
    public UniversityExamRecordException(
            BusinessExceptionType exceptionType) {
        super(exceptionType);
    }
}
