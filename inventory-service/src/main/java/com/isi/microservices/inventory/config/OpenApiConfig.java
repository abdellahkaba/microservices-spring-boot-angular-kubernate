package com.isi.microservices.inventory.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI inventoryServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("API du service d'inventaire")
                        .description("Il s'agit de l'API REST pour Inventory Service.")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Vous pouvez vous référer à la documentation wiki du service d'inventaire")
                        .url("https://inventory-service-dummy-url.com/docs"));
    }
}
