package com.mergetechng.jobs.configs.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerOpenApiConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MergetechNg Jobs API")
                        .version("1.0.0")
                        .termsOfService("https://jobs.mergetechng.com/terms-of-service")
                        .contact(new Contact()
                                .email("devmergetechng@gmail.com")
                                .name("MergeTechJobsApi Developer")
                                .url("https://jobs.mergetechng.com/contact")
                        )
                ).components(new Components()
                        .addSecuritySchemes("authentication", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("missingParam"))
                ).addSecurityItem(new SecurityRequirement().addList("mySecretHeader"));
    }
}