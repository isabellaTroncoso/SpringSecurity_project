package org.example.springboot_project.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;



/**
 * denna klass dyker upp i swagger när vi tar in vår JWT-token i autentisering,
 * och ger info om vad för typ att autentisering vi får
 */
@OpenAPIDefinition(
        info = @Info(title = "API med JWT"
                , version = "1.0"),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth"
        ,
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
        ,
        bearerFormat = "JWT"
)
public class OpenApiConfig {
}
