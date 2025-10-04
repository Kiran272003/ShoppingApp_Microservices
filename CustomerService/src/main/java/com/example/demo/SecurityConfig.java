package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration // Marks this as a Spring Security configuration class
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF since this is an API
            .csrf(csrf -> csrf.disable())

            // Add custom filter that authenticates requests using headers from the gateway
            .addFilterBefore(
                new HeaderAuthenticationFilter(),
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class
            )

            // Authorization rules for endpoints
            .authorizeHttpRequests(auth -> auth
                // Customer endpoints accessible to USER and ADMIN
                .requestMatchers("/customer", "/addCustomer", "/getProducts",
                                 "/deleteCustomer", "/getById", "/updateCustomer")
                    .hasAnyRole("USER", "ADMIN")

                // Order endpoints accessible only to USER
                .requestMatchers("/getMyOrders", "/placeOrder", "/deleteOrder")
                    .hasRole("USER")

                // All other requests must be authenticated
                .anyRequest().authenticated()
            )

            // Disable HTTP Basic login (auth handled via headers)
            .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }
}
