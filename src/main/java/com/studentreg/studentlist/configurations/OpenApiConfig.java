package com.studentreg.studentlist.configurations;

//import springfox.documentation.builders.PathSelectors;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.builders.RequestHandlerSelectors;
import org.springframework.context.annotation.Bean;


@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI () {
        return new OpenAPI()
        .info(
                new Info()
                .title("REST API Documentation")
                .version("1.0")
                .description("Documentation using SpringDoc OpenAPI 3")
                .contact(new Contact().name("Ori").email("oriel.keza@gmail.com")));
    }
}
