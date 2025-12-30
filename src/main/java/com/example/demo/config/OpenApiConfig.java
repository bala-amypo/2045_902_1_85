package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // Define Bearer JWT security scheme
        SecurityScheme bearerAuthScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        // Apply security globally
        SecurityRequirement securityRequirement =
                new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                // Server configuration (your existing config preserved)
                .servers(List.of(
                        new Server().url("https://9362.pro604cr.amypo.a/")
                ))

                // Register security scheme
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", bearerAuthScheme)
                )

                // Enforce security for all endpoints (Swagger only)
                .addSecurityItem(securityRequirement);
    }
}
