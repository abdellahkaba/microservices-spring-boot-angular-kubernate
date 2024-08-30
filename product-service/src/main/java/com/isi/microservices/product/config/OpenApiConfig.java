package com.isi.microservices.product.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ProductServiceApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST pour Service Product")
                        .description("Le REST Api pour le Produit")
                        .version("1.0")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Referez-vous a la Documentation Wiki du Product Service")
                        .url("https://product-service-dummy-url.com/docs"));
    }
}
