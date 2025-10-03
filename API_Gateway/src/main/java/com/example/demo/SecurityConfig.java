//package com.example.demo;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//	@Bean
//	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//
//	    RedirectServerAuthenticationSuccessHandler successHandler =
//	        new RedirectServerAuthenticationSuccessHandler("/productservice/product"); 
//	    http
//	        .authorizeExchange(exchanges -> exchanges
//	            .pathMatchers(HttpMethod.GET,  "/customerservice/**", "/ordersservice/**","/productservice/**").permitAll()
//	            .anyExchange().authenticated()
//	        )
//	        .oauth2Login(oauth -> oauth.authenticationSuccessHandler(successHandler))
//	        .csrf(ServerHttpSecurity.CsrfSpec::disable);
//	    return http.build();
//	}
//
//}



