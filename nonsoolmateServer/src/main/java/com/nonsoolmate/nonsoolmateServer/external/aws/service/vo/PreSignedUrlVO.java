package com.nonsoolmate.nonsoolmateServer.external.aws.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PreSignedUrlVO {
    private String fileName;
    private String url;

    public static PreSignedUrlVO of(String fileName, String url) {
        return new PreSignedUrlVO(fileName, url);
    }
}
