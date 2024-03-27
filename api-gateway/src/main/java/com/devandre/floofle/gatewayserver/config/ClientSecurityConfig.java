package com.devandre.floofle.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author Andre on 27/03/2024
 * @project Floof Project
 */
@Configuration
@EnableWebFluxSecurity
public class ClientSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.
                csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .authorizeExchange(
                        exchanges -> exchanges
                                .pathMatchers("/authenticate").authenticated()
                                .anyExchange().permitAll()
                );
        http.oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
