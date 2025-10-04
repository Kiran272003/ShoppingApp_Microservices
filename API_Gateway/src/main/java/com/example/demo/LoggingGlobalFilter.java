package com.example.demo;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component // Registers this as a Spring-managed bean
public class LoggingGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggingGlobalFilter.class);

    // Log incoming request URI and outgoing response status
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI requestUri = exchange.getRequest().getURI();
        logger.info("APIGATE - Incoming request URI: {}", requestUri);

        return chain.filter(exchange).then(
            Mono.fromRunnable(() -> {
                logger.info("APIGATE - Response status code: {}", exchange.getResponse().getStatusCode());
            })
        );
    }

    // Defines filter execution order (lower value = higher priority)
    @Override
    public int getOrder() {
        return 0;
    }
}
