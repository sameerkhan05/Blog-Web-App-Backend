package com.sam.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Sameer Blog API")
						.description("Documentation for Blog APIs")
						.version("1.0")
						.contact(new Contact()
								.name("Sameer Khan")
								.email("sameerkhanyt09@gmail.com")
								.url("https://github.com/sameerkhan05/Blog-Web-App-Backend")))
				.components(new io.swagger.v3.oas.models.Components()
						.addSecuritySchemes("BearerAuth", new SecurityScheme()
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")))
				.addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
	}
}
