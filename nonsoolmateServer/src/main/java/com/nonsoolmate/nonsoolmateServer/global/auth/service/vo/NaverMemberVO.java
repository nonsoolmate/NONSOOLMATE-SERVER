package com.nonsoolmate.nonsoolmateServer.global.auth.service.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverMemberVO {
    private String resultCode;
    private String message;

    @JsonProperty("response")
    private Response response;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {
        private String gender;
        private String email;
        private String mobile;
        private String name;
        private String birthyear;
    }
}