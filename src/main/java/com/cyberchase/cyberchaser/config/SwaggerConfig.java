package com.cyberchase.cyberchaser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cyberchase.cyberchaser.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    public ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
            .title("Cyberchaser Encryption/Hashing Tool")
            .description("For CS 4389.001")
            .version("1.0.0")
            .build();
    }
}


/*
TO RUN:
in terminal, navigate to cs4389-encryption-library/gs-spring-boot-master/gs-spring-boot-master/complete
for PC users: execute $ ./mvnw.cmd spring-boot:run
for Mac users: execute $ mvn spring-boot:run
enter http://localhost:8080/swagger-ui.html#/encryption-controller into your web browser
to stop running: execute $ ^C (control + C)
*/
