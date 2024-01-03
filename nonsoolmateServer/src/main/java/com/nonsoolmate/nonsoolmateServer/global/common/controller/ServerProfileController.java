package com.nonsoolmate.nonsoolmateServer.global.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ServerProfileController {

    private final Environment env;

    @GetMapping("/profile")
    public String getProfile() {
        return env.getActiveProfiles()[0];
    }
}