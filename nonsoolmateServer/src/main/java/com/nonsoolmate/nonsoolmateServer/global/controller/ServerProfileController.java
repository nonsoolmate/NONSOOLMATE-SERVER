package com.nonsoolmate.nonsoolmateServer.global.controller;

import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.CommonSuccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ServerProfileController {

    private final Environment env;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<String>> getProfile() {
        return ResponseEntity.ok()
                .body(ApiResponse.success(CommonSuccessType.GET_SERVER_PROFILE, env.getActiveProfiles()[0]));
    }
}