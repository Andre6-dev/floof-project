package com.devandre.floofle.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestCustomizers;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

/**
 * @author Andre on 27/03/2024
 * @project Floof Project
 */
@Configuration
@EnableWebFluxSecurity
public class ClientSecurityConfig {

    private final ReactiveClientRegistrationRepository repository;

    public ClientSecurityConfig(ReactiveClientRegistrationRepository repository) {
        this.repository = repository;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, ServerOAuth2AuthorizationRequestResolver resolver) {
        http.
                csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .authorizeExchange(
                        exchanges -> exchanges
                                .pathMatchers("/**").permitAll()
                                .pathMatchers("/logout").permitAll()
                                .pathMatchers("/authenticate").authenticated()
                                .anyExchange().permitAll()
                );
        http.oauth2Login(auth ->
                auth.authorizationRequestResolver(resolver)
        );
        http.oauth2Client(Customizer.withDefaults())
                .logout(
                        logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessHandler(logoutSuccessHandler(repository))
                );
        return http.build();
    }

    @Bean
    ServerLogoutSuccessHandler logoutSuccessHandler(ReactiveClientRegistrationRepository repository) {
        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutHandler = new OidcClientInitiatedServerLogoutSuccessHandler(repository);
        oidcLogoutHandler.setPostLogoutRedirectUri("http://127.0.0.1:8090/landing");
        return oidcLogoutHandler;
    }

    @Bean
    public ServerOAuth2AuthorizationRequestResolver pkceResolver(ReactiveClientRegistrationRepository repo) {
        DefaultServerOAuth2AuthorizationRequestResolver resolver = new DefaultServerOAuth2AuthorizationRequestResolver(repo);
        resolver.setAuthorizationRequestCustomizer(OAuth2AuthorizationRequestCustomizers.withPkce());
        return resolver;
    }
}
