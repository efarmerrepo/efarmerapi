package com.efarmer.api.efarmer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Component
public class OpenApiConfiguration {

	@Bean
	public OpenAPI efarmaService() {
		return new OpenAPI().info(new Info().title("E-farmer").description("Efarmer APi's").version("1.0.0"));
	}

}
