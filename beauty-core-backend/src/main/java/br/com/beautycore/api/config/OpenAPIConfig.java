package br.com.beautycore.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition
@Configuration
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer")
public class OpenAPIConfig {

    @Bean
    public OpenAPI stockControllerAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Beauty Core API")
                        .description("Gerenciamento para salão de beleza")
                        .version("1.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/cauerg44/beauty-ledger-platform")));
    }
}