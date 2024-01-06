package com.nonsoolmate.nonsoolmateServer.global.common.controller;

import com.nonsoolmate.nonsoolmateServer.global.common.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.CommonSuccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ServerProfileController {

    private final Environment env;

    @GetMapping("/profile")
    public ApiResponse<String> getProfile() {
        return ApiResponse.success(CommonSuccessType.GET_SERVER_PROFILE, env.getActiveProfiles()[0]);
    }
}