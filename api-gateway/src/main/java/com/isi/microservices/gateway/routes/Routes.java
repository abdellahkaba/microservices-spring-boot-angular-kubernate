package com.isi.microservices.gateway.routes;


import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class Routes {


    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(){
        return route("product_service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080"))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute(){
        return route("order_service")
                .route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute(){
        return route("inventory_service")
                .route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082"))
                .build();
    }

}
