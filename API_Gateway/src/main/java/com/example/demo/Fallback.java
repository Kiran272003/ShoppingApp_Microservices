package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController // Marks this class as a REST controller to handle HTTP requests
public class Fallback {

    // Fallback endpoint for Product Service when it's unavailable
    @GetMapping("fallback/products")
    public Mono<String> productServiceFallback() {
        return Mono.just("Product Service not available");
    }

    // Fallback endpoint for Customer Service when it's unavailable
    @GetMapping("/fallback/customer")
    public Mono<String> customerServiceFallback() {
        return Mono.just("Customer Service not available");
    }

    // Fallback endpoint for Order Service when it's unavailable
    @GetMapping("/fallback/orders")
    public Mono<String> orderServiceFallback() {
        return Mono.just("Order Service not available");
    }
}
