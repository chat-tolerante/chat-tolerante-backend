package com.chat.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestfulApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(RestfulApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Autowired
			Environment environment;
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String frontUrl = this.environment.getProperty("custom.front-url");
				registry.addMapping("/**").allowCredentials(true).allowedOrigins(frontUrl);
			}
		};
	}


}
