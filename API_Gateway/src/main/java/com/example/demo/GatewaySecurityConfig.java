package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

import java.util.stream.Collectors;
import java.util.List;

@Configuration // Marks this class as a Spring configuration class
public class GatewaySecurityConfig {

    // Password encoder bean (BCrypt for secure password hashing)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // In-memory user store with reactive support
    @Bean
    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("adminpass"))
                .roles("ADMIN", "USER") // admin has both ADMIN and USER roles
                .build();

        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("userpass"))
                .roles("USER") // regular user
                .build();

        return new MapReactiveUserDetailsService(admin, user);
    }

    // Security filter chain configuration
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable())     // disable CSRF (not needed for APIs)
            .httpBasic(b -> {})               // enable HTTP Basic authentication
            .formLogin(form -> form.disable()) // disable form login
            .authorizeExchange(exchanges -> exchanges
                .anyExchange().authenticated() // require authentication for all requests
            );
        return http.build();
    }

    // GlobalFilter to forward authenticated user details (username + roles) as headers
    @Bean
    public GlobalFilter addAuthHeadersFilter() {
        return (exchange, chain) -> 
            exchange.getPrincipal()
                .flatMap(principal -> {
                    if (principal instanceof org.springframework.security.core.Authentication auth) {
                        String username = auth.getName();

                        List<String> roles = auth.getAuthorities().stream()
                                .map(Object::toString)
                                .collect(Collectors.toList());

                        String rolesHeader = roles.isEmpty() ? "" : String.join(",", roles);

                        // Mutate request to add custom headers
                        ServerWebExchange mutated = exchange.mutate()
                            .request(exchange.getRequest().mutate()
                                .header("X-Auth-Username", username)
                                .header("X-Auth-Roles", rolesHeader)
                                .build())
                            .build();

                        return chain.filter(mutated);
                    }
                    return chain.filter(exchange); // proceed if no authentication
                })
                .switchIfEmpty(chain.filter(exchange)); // proceed if principal is empty
    }
}
