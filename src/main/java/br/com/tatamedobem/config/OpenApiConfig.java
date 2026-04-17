package br.com.tatamedobem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI tatameDoBemOpenApi() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .info(new Info()
                        .title("Tatame do Bem API")
                        .description("API para gerenciamento de projetos sociais de Jiu-Jitsu.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Tatame do Bem")
                                .email("contato@tatamedobem.local"))
                        .license(new License()
                                .name("Uso educacional")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
