package com.example.demo;

import com.example.demo.HeaderAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .addFilterBefore(new HeaderAuthenticationFilter(), org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> auth
                // Product endpoints
                .requestMatchers("/customer", "/addCustomer", "/getProducts","/deleteCustomer","/getById","/updateCustomer").hasAnyRole("USER","ADMIN")
                .requestMatchers("/getMyOrders", "/placeOrder","/deleteOrder").hasRole("USER")
                // allow actuator or swagger if needed - adjust as required
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> httpBasic.disable()); // requests authenticated by header, no local basic

        return http.build();
    }
}
