package com.hkrishna.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfig {

    @Bean
    public RouteLocator gateWayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p-> p.path("/get")
                        .filters(f -> f.addRequestHeader("demoHeader","requestFromHari"))
                        .uri("http://httpbin.org:80"))
                .route(p-> p.path("/currency-conversion/**") //if this path matches redirect it to eureka
                        .uri("lb://currency-conversion")) // talk to eureka lb:load-balance
                .route(p-> p.path("/currency-exchange/**") //if this path matches redirect it to eureka
                        .uri("lb://currency-exchange")) // talk to eureka lb:load-balance
                .route(p-> p.path("/currency-conversion-feign/**") //if this path matches redirect it to eureka
                        .uri("lb://currency-conversion")) // talk to eureka lb:load-balance
                .build();
    }
}
