package com.nonsoolmate.nonsoolmateServer.external.aws.error;

import com.nonsoolmate.nonsoolmateServer.global.error.exception.BusinessExceptionType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AWSExceptionType implements BusinessExceptionType {
    DELETE_FILE_AWS_S3_FAIL(HttpStatus.BAD_REQUEST, "S3 파일 삭제에 실패했습니다"),
    NOT_FOUND_FILE_AWS_S3(HttpStatus.BAD_REQUEST, "S3에서 파일을 찾을 수 없습니다"),
    GET_PRESIGNED_URL_AWS_CLOUDFRONT_FAIL(HttpStatus.BAD_REQUEST, "Cloud PresignedUrl 발급에 실패했습니다"),
    NOT_FOUND_CLOUD_PRIVATE_KEY_FAIL(HttpStatus.NOT_FOUND, "Cloud Private Key 조회에 실패했습니다"),
    INVALID_KEY_SPEC_FAIL(HttpStatus.BAD_REQUEST, "Cloud Private Key Spec이 잘못되었습니다");

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
