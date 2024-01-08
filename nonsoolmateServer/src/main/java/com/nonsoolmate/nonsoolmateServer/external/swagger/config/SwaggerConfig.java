package com.nonsoolmate.nonsoolmateServer.external.swagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "NONSOOLMATE",
                description = "NONSOOLMATE SERVER API Documentation",
                version = "v1"))
@Configuration
public class SwaggerConfig {
}
