package com.nonsoolmate.nonsoolmateServer.external.aws.error;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.ClientException;

public class AWSException extends ClientException {
    public AWSException(
            AWSExceptionType exceptionType) {
        super(exceptionType);
    }
}
