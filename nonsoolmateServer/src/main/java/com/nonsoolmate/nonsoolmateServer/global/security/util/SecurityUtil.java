package com.nonsoolmate.nonsoolmateServer.global.security.util;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.global.security.CustomAuthUser;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    private static final String ANONYMOUS_USER = "anonymousUser";

    public static Member getLoginMember() {
        return getLoginMemberAuthInfo().getMember();
    }

    private static CustomAuthUser getLoginMemberAuthInfo() {
        return (CustomAuthUser)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Map<String, Object> getLoginMemberInfo() {
        Map<String, Object> memberInfoMap = new HashMap<>();
        memberInfoMap.put("member", getLoginMember());
        return memberInfoMap;
    }

    private static Optional<CustomAuthUser> getUserAuthDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.equals(ANONYMOUS_USER)) {
            return Optional.empty();
        }
        return Optional.of((CustomAuthUser) principal);
    }
}
