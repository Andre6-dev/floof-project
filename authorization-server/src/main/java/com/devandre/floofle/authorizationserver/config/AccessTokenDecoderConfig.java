package com.devandre.floofle.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;

import java.security.interfaces.RSAPublicKey;

/**
 * @author Andre on 25/03/2024
 * @project Floof Project
 */
@Configuration
public class AccessTokenDecoderConfig {

    /**
     * This method is responsible for creating an AuthenticationManager bean that can be used to authenticate bearer tokens.
     * It first creates a JwtDecoder by using the public key from the RSA key pair generated in the KeyPairConfig class.
     * The JwtDecoder is configured to use the RS256 signature algorithm.
     * The JwtDecoder is then used to create a JwtAuthenticationProvider, which is used to create the AuthenticationManager.
     *
     * @return an AuthenticationManager that can be used to authenticate bearer tokens
     */
    @Bean
    public AuthenticationManager bearerTokenAuthenticationManager()  {
        JwtDecoder decoder = NimbusJwtDecoder.withPublicKey((RSAPublicKey) KeyPairConfig.getKeyPair().getPublic())
                .signatureAlgorithm(SignatureAlgorithm.RS256)
                .build();

        return new ProviderManager(new JwtAuthenticationProvider(decoder));
    }
}
