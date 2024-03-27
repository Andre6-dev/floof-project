package com.devandre.floofle.authorizationserver.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * This class is responsible for generating and providing RSA key pairs for the authorization server.
 * It is marked as a configuration class, meaning it will be instantiated and managed by Spring.
 * It also provides a JWKSource bean, which is used to select JWKs for processing JOSE objects.
 *
 * @author Andre on 25/03/2024
 * @project Floof Project
 */
@Configuration
@Slf4j
public class KeyPairConfig {

    @Getter
    private static KeyPair keyPair;

    static {
        try {
            keyPair = generateRsaKey();
        } catch (NoSuchAlgorithmException e) {
            log.error("Error generating RSA key pair", e);
        }
    }

    /**
     * Provides a JWKSource bean. This bean is used to select JWKs for processing JOSE objects.
     *
     * @return a JWKSource
     * @throws NoSuchAlgorithmException if the RSA algorithm is not available
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);

        return ((jwkSelector, context) -> jwkSelector.select(jwkSet));
    }

    private RSAKey generateRsa() {

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    public static KeyPair generateRsaKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

}
