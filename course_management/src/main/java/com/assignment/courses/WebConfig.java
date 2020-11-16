package com.assignment.courses;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebConfig implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
	  
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Methods", 
            		"Access-Control-Request-Method", "Access-Control-Request-Headers",
            		"x-requested-with", "authorization", "Content-Type",
            		"Authorization", "credential", "X-XSRF-TOKEN")
            .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Methods", "Access-Control-Request-Method", "Access-Control-Request-Headers");
            /* .allowCredentials(true).maxAge(3600); */

    }
}