package com.nonsoolmate.nonsoolmateServer.external.oauth.service;

import com.nonsoolmate.nonsoolmateServer.domain.auth.service.AuthService;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.member.repository.MemberRepository;
import com.nonsoolmate.nonsoolmateServer.domain.auth.controller.dto.request.MemberRequestDTO;
import com.nonsoolmate.nonsoolmateServer.domain.auth.service.vo.MemberSignUpVO;
import com.nonsoolmate.nonsoolmateServer.external.oauth.service.vo.NaverMemberVO;
import com.nonsoolmate.nonsoolmateServer.external.oauth.service.vo.NaverTokenVO;
import com.nonsoolmate.nonsoolmateServer.external.oauth.service.vo.enums.AuthType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

@Service
public class NaverAuthService extends AuthService {

    @Value("${naver.client-id}")
    private String clientId;
    @Value("${naver.client-secret}")
    private String clientSecret;
    @Value("${naver.state}")
    private String state;

    public NaverAuthService(MemberRepository memberRepository) {
        super(memberRepository);
    }

    @Override
    public MemberSignUpVO saveMemberOrLogin(String authorizationCode, MemberRequestDTO request) {
        String accessToken = getAccessToken(authorizationCode, clientId, clientSecret, state).getAccess_token();
        System.out.println("accessToken = " + accessToken);
        NaverMemberVO naverMemberInfo = getNaverMemberInfo(accessToken);

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
//    private NaverMemberVO getNaverMemberInfo(String accessToken) {
//        try {
//            WebClient webClient = WebClient.builder().build();
//            return webClient.get()
//                    .uri("https://openapi.naver.com/v1/nid/me")
//                    .headers(headers -> headers.setBearerAuth(accessToken))
//                    .retrieve()
//                    .bodyToMono(NaverMemberVO.class)
//                    .block();
//        } catch (WebClientResponseException e) {
//            throw new AuthException(AuthExceptionType.INVALID_MEMBER_PLATFORM_TOKEN);
//        }
//    }

    public NaverMemberVO getNaverMemberInfo(String accessToken) {
        WebClient webClient = WebClient.builder().build();
        return webClient.post()
                .uri("https://openapi.naver.com/v1/nid/me")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(NaverMemberVO.class)
                .block();
    }

    private NaverTokenVO getAccessToken(String authorizationCode, String clientId, String clientSecret, String state) {
        WebClient webClient = WebClient.builder().build();
        return webClient.post()
                .uri(uriBuilder ->
                        uriBuilder
                                .scheme("https")  // 스킴을 명시적으로 지정
                                .host("nid.naver.com")  // 호스트를 명시적으로 지정
                                .path("/oauth2.0/token")
                                .queryParam("grant_type", "authorization_code")
                                .queryParam("client_id", clientId)
                                .queryParam("client_secret", clientSecret)
                                .queryParam("code", authorizationCode)
                                .queryParam("state", state)
                                .build()
                )
                .retrieve()
                .bodyToMono(NaverTokenVO.class)
                .block();
    }
}
