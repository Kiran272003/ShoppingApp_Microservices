package com.example.demo;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;

@Configuration // Marks this as a configuration class for Spring context
public class AppConfig {

    // Creates a RestTemplate bean with client-side load balancing (via Ribbon/Spring Cloud LoadBalancer)
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
