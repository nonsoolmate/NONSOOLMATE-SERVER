package com.nonsoolmate.nonsoolmateServer.global.security.config;

import com.nonsoolmate.nonsoolmateServer.global.security.filter.JwtAuthenticationFilter;
import com.nonsoolmate.nonsoolmateServer.global.security.filter.JwtExceptionFilter;
import io.swagger.v3.oas.models.PathItem.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    public static final String[] AUTH_WHITELIST = {
            "/", "/error",

             "/favicon.ico",

            "/actuator/health", "/check/profile"
    };

    public static final String[] AUTH_WHITELIST_WILDCARD = {
            "/webjars/**",

            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/auth/**", "/login/**",

            "/css/**", "/images/**", "/js/**",

            "/h2-console/**",
    };

    @Value("${spring.web.domain.server}")
    private String serverDomain;

    @Value("${spring.web.domain.client}")
    private String clientDomain;

    @Value("${spring.web.domain.client-local}")
    private String clientLocalDomain;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(serverDomain, clientDomain, clientLocalDomain)
                        .allowedOriginPatterns(serverDomain, clientDomain, clientLocalDomain)
                        .allowedHeaders("*")
                        .allowedMethods(
                                HttpMethod.GET.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.PATCH.name()
                        );
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable()
                        )
                )
        ;

        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers(AUTH_WHITELIST).permitAll();
                    auth.requestMatchers(AUTH_WHITELIST_WILDCARD).permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
