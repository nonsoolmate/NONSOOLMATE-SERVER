package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessException;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;

public class SelectUniversityException extends BusinessException {
    public SelectUniversityException(BusinessExceptionType exceptionType) {super(exceptionType);}
}
