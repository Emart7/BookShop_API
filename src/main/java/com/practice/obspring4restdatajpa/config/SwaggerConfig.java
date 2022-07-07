package com.practice.obspring4restdatajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Configuración Swagger para la generación de documentación de la API REST
 * http://localhost:8080/swagger-ui/
 * http://localhost:8080/v2/api-docs
 */

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Spring Boot Book API REST",
                "Library API REST docs",
                "1.0",
                "http://www.poderyconocimiento.com",
                new Contact(
                        "Euler Example",
                        "http://www.poderyconocimiento.com",
                        "euler@example.com"),
                        "MIT",
                        "http://www.poderyconocimiento.com",
                        Collections.emptyList());
    }
}
