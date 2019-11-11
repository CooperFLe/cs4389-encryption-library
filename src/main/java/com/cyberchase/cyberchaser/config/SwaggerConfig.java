package com.cyberchase.cyberchaser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .build();
//                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Cyberchaser Encryption/Decryption Tool",
                "For CS 4389.001",
                "API TOS",
                null,
                null,
                null,
                null,
                Collections.emptyList());
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
