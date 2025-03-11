package com.javaExpress.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class UserConfiguration {

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(
						new Info()
						.title("SpringShop API")
						.description("User Cedential")
						.version("v0.0.1")
						.license(new License().name("Apache 2.0").url("http://javaExpress.org")))
				.externalDocs(
						new ExternalDocumentation()
						.description("This is just For practise purpose")
						.url("https://www.javaExpress.com"));
	}

}
