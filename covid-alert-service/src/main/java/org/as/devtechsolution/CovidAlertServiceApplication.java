package org.as.devtechsolution;

/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2WebMvc
public class CovidAlertServiceApplication {

	public static void main(String[] args) {
		final var run = SpringApplication.run(CovidAlertServiceApplication.class, args);
	}

	@Bean
	public Docket apiDocket ( ) {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}
}
