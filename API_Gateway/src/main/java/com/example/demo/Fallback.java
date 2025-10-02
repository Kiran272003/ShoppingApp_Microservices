package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class Fallback {

	@GetMapping("fallback/products")
	public Mono<String> productServiceFallback()
	{
		return Mono.just("Product Service not available");
	}
	
	@GetMapping("/fallback/customer")
	public Mono<String> customerServiceFallback()
	{
		return Mono.just("Customer Service not available");
		
	}
	
	@GetMapping("/fallback/orders")
	public Mono<String> orderServiceFallback()
	{
		return Mono.just("Order Service not available");
	}
}
