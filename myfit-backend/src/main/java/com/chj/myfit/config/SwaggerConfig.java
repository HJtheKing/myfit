package com.chj.myfit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Spring Board REST API")
				.description("myfit REST API입니다.")
				.version("v0.0.1")
				.license(new License().name("CHJ").url("http://www.naver.com")));
	}
}
