package com.nonsoolmate.nonsoolmateServer.external.swagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "NONSOOLMATE",
                description = "NONSOOLMATE SERVER API Documentation",
                version = "v1"),
        security = {
                @SecurityRequirement(name = "Authorization"),
                @SecurityRequirement(name = "Authorization-refresh")
        },
        servers = {
                @Server(url = "http://localhost:8080", description = "local server"),
                @Server(url = "https://api.nonsoolmate.com", description = "prd server")
        })
@SecuritySchemes({
        @SecurityScheme(name = "Authorization",
                type = SecuritySchemeType.APIKEY,
                description = "access token",
                in = SecuritySchemeIn.HEADER,
                paramName = "Authorization"),
        @SecurityScheme(name = "Authorization-refresh",
                type = SecuritySchemeType.APIKEY,
                description = "refresh token",
                in = SecuritySchemeIn.HEADER,
                paramName = "Authorization-refresh"),
})
@Configuration
public class SwaggerConfig {

}
