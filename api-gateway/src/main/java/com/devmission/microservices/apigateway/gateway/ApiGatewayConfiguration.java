package com.devmission.microservices.apigateway.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {


    // instead of providing urls like http://localhost:8765/currency-exchange/exchange/from/USD/to/EUR
    // you can provide http://localhost:8765/exchange/from/USD/to/EUR to the gateway
    // just as you would provide them to the currency-exchange microservice directly

    // the gateway is resolving the currency exchange service using route redirecting/re-routing instead of client discovery property
    // the gateway becomes transparent to the user


    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                // for any currency-exchange request
                .route(predicateSpec -> predicateSpec.path("/exchange/**")
                        // send to feign client currency-exchange using Eureka
                        // and apply load balancing (lb://)
                        .uri("lb://currency-exchange"))
                .route(predicateSpec -> predicateSpec.path("/conversion/**")
                        .uri("lb://currency-conversion"))

                // any URI can be intercepted and redirected
                .route(predicateSpec -> predicateSpec.path("/conversion-new/**")
                        .filters(gatewayFilterSpec ->
                                // modifies URI, but keeps the path variables
                                gatewayFilterSpec.rewritePath("/conversion-new/(?<segment>.*)", "/conversion/${segment}"))
                        .uri("lb://currency-conversion"))
                .build();
    }
}
