package com.nonsoolmate.nonsoolmateServer.global.auth.service.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberSignUpVO {
    private final Long memberId;
    private final String email;
    private final String memberName;
}
