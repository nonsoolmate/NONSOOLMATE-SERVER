package com.nonsoolmate.nonsoolmateServer.external.aws.error;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AWSExceptionType implements BusinessExceptionType {
    DELETE_FILE_AWS_S3_FAIL(HttpStatus.BAD_REQUEST, "올바른 답안지 파일 이름이 아닙니다."),
    NOT_FOUND_SHEET_FILE_AWS_S3(HttpStatus.NOT_FOUND, "올바른 답안지 파일 이름을 찾을 수 없습니다"),
    GET_PRESIGNED_URL_AWS_CLOUDFRONT_FAIL(HttpStatus.BAD_REQUEST, "Cloud PresignedUrl 발급에 실패했습니다");

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus status() {
        return this.status;
    }

    @Override
    public String message() {
        return this.message;
    }
}
