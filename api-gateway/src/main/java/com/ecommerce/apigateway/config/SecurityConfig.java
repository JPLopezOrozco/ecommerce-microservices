package com.ecommerce.apigateway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth->
                        oauth
                                .jwt(Customizer.withDefaults())
                );
        return http.build();
    }

    @Bean
    public GlobalFilter tokenRelayFilter() {
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                ServerWebExchange mutatedExchange = exchange.mutate()
                        .request(r-> r.header(HttpHeaders.AUTHORIZATION, authHeader))
                        .build();
                return chain.filter(mutatedExchange);
            }
            return chain.filter(exchange);
        };
    }



}
