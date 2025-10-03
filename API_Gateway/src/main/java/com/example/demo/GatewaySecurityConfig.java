package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class GatewaySecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // In-memory reactive users
    @Bean
    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("adminpass"))
                .roles("ADMIN","USER")
                .build();

        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("userpass"))
                .roles("USER")
                .build();

        return new MapReactiveUserDetailsService(admin, user);
    }

    // Security chain: require authentication for everything (adjust paths if you want public endpoints)
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable())
            .httpBasic(b -> {})               // enable HTTP Basic
            .formLogin(form -> form.disable())
            .authorizeExchange(exchanges -> exchanges
                .anyExchange().authenticated()
            );
        return http.build();
    }

    // GlobalFilter to add username and roles to forwarded requests
    @Bean
    public GlobalFilter addAuthHeadersFilter() {
        return (exchange, chain) -> 
            exchange.getPrincipal()
                .flatMap(principal -> {
                    if (principal instanceof Authentication auth) {
                        String username = auth.getName();

                        List<String> roles = auth.getAuthorities().stream()
                                .map(Object::toString)
                                .collect(Collectors.toList());

                        String rolesHeader = roles.isEmpty() ? "" : String.join(",", roles);

                        ServerWebExchange mutated = exchange.mutate()
                            .request(exchange.getRequest().mutate()
                                .header("X-Auth-Username", username)
                                .header("X-Auth-Roles", rolesHeader)
                                .build())
                            .build();

                        return chain.filter(mutated);
                    }
                    return chain.filter(exchange);
                })
                .switchIfEmpty(chain.filter(exchange));
    }

}
