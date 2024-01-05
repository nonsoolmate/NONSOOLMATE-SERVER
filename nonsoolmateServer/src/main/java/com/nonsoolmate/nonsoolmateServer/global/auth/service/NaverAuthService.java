package com.nonsoolmate.nonsoolmateServer.global.auth.service;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.member.repository.MemberRepository;
import com.nonsoolmate.nonsoolmateServer.global.auth.controller.dto.MemberRequestDTO;
import com.nonsoolmate.nonsoolmateServer.global.auth.service.vo.MemberSignUpVO;
import com.nonsoolmate.nonsoolmateServer.global.auth.service.vo.NaverMemberVO;
import com.nonsoolmate.nonsoolmateServer.global.auth.service.vo.enums.AuthType;
import jakarta.security.auth.message.AuthException;
import java.util.Map;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class NaverAuthService extends AuthService {

    private final WebClient webClient;

    public NaverAuthService(MemberRepository memberRepository, WebClient webClient) {
        super(memberRepository);
        this.webClient = webClient;
    }

    @Override
    public MemberSignUpVO saveMemberOrLogin(String platformToken, MemberRequestDTO request) {
        NaverMemberVO naverMemberInfo = getNaverMemberInfo(platformToken);

        Member foundMember = getMember(request.platformType(), naverMemberInfo.getResponse().getEmail());
        if (foundMember != null) {
            return MemberSignUpVO.of(foundMember, request.platformType(), AuthType.LOGIN);
        }
        Member savedMember = saveUser(request, naverMemberInfo.getResponse().getEmail(),
                naverMemberInfo.getResponse().getName(),
                naverMemberInfo.getResponse().getBirthyear(), naverMemberInfo.getResponse().getGender(),
                naverMemberInfo.getResponse().getMobile());

        return MemberSignUpVO.of(savedMember, request.platformType(), AuthType.SIGN_UP);
    }

    // TODO:: 토큰 만료시에 대한 에러 추가
    // TODO:: Auth 패키지 따로 뺄까?
    // TODO:: uri yml로 빼야지..
    private NaverMemberVO getNaverMemberInfo(String accessToken) {
        try {
            return webClient.get()
                    .uri("https://openapi.naver.com/v1/nid/me")
                    .headers(headers -> headers.setBearerAuth(accessToken))
                    .retrieve()
                    .bodyToMono(NaverMemberVO.class)
                    .block();
        } catch (WebClientResponseException e) {
            // WebClientResponseException이 발생하면 해당 예외를 throw
            throw new RuntimeException("Error while fetching Naver member info", e);
        }
    }
}
