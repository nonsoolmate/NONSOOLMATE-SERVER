package com.nonsoolmate.nonsoolmateServer.domain.member.exception;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessException;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;

public class MemberException extends BusinessException {
    public MemberException(BusinessExceptionType exceptionType) {
        super(exceptionType);
    }
}
