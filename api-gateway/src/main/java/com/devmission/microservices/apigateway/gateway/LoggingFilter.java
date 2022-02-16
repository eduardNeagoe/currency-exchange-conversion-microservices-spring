package com.devmission.microservices.apigateway.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;

@Slf4j
@Component
// demonstrates how to implement a Spring Cloud Gateway Logging Filter
public class LoggingFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info(MessageFormat.format("The path of the request: {0}", exchange.getRequest().getPath()));
        // let the execution continue
        return chain.filter(exchange);
    }
}
