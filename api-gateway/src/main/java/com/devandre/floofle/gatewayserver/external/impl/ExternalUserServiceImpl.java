package com.devandre.floofle.gatewayserver.external.impl;

import com.devandre.floofle.gatewayserver.dto.response.UserInfoResponseDto;
import com.devandre.floofle.gatewayserver.external.ExternalUserService;
import com.devandre.floofle.gatewayserver.external.response.UserResponseDto;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author Andre on 27/03/2024
 * @project Floof Project
 */
@Service
@Slf4j
public class ExternalUserServiceImpl implements ExternalUserService {

    private final WebClient.Builder webClientBuilder;



    public ExternalUserServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<UserInfoResponseDto> getUser(String auth) {
        return webClientBuilder.baseUrl("http://localhost:9000")
                .build().get()
                .uri(uriBuilder -> uriBuilder.path("/auth/users")
                        .build()
                )
                .header(AUTHORIZATION, auth)
                .retrieve()
                .bodyToMono(UserResponseDto.class)
                .map(userResponseDto -> {
                    UserInfoResponseDto dto = new UserInfoResponseDto();
                    dto.setId(userResponseDto.getId());
                    dto.setUsername(userResponseDto.getUsername());
                    dto.setEmail(userResponseDto.getEmail());
                    return dto;
                })
                .log();
    }

    private Mono<UserInfoResponseDto> authorizationServerFallback(String auth, CallNotPermittedException e){
        return authorizationServerFallback(auth, (WebClientResponseException) null);
    }
    private Mono<UserInfoResponseDto> authorizationServerFallback(String auth, WebClientRequestException e){
        return authorizationServerFallback(auth, (WebClientResponseException) null);
    }

    private Mono<UserInfoResponseDto> authorizationServerFallback(String auth, WebClientResponseException e){
        log.error("dont able connect to authorization server for get user info");
        return Mono.just(new UserInfoResponseDto());
    }
}
