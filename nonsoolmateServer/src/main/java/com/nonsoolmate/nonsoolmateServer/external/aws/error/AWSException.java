package com.nonsoolmate.nonsoolmateServer.external.aws.error;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessException;

public class AWSException extends BusinessException {
    public AWSException(
            AWSExceptionType exceptionType) {
        super(exceptionType);
    }
}
