package com.nonsoolmate.nonsoolmateServer.global.security.config;

import io.swagger.v3.oas.models.PathItem.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/", "/error", "/webjars/**",

            // Swagger
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**",

            // Authentication
            "/auth/**",

            // client
            "/", "/css/**", "/images/**", "/js/**", "/favicon.ico", "/h2-console/**"
    };


    //cors 에러를 대응하기 위한 메소드
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://nonsoolmate.com", "localhost:3000")
                        .allowedOriginPatterns("https://nonsoolmate.com", "localhost:3000")
                        .allowedMethods(
                                HttpMethod.GET.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.PATCH.name()
                        );
            }
        };
    }


}
