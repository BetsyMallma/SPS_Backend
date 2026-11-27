package com.iestp.sps.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// Permite que el Frontend (React) llame a esta API desde otro puerto
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:5173", "http://localhost:3000")
		.allowedMethods("GET", "POST", "PUT", "DELETE")
		.allowedHeaders("*");
	}
}
