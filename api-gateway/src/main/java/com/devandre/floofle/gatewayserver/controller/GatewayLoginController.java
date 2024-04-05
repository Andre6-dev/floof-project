package com.devandre.floofle.gatewayserver.controller;

import com.devandre.floofle.gatewayserver.dto.response.TokenInfoResponseDto;
import com.devandre.floofle.gatewayserver.dto.response.UserInfoResponseDto;
import com.devandre.floofle.gatewayserver.external.ExternalUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.devandre.floofle.gatewayserver.util.OauthTokenUtil.extractAuthority;
import static com.devandre.floofle.gatewayserver.util.OauthTokenUtil.getAuth;

/**
 * @author Andre on 27/03/2024
 * @project Floof Project
 */
@RestController
@Slf4j
public class GatewayLoginController {

    private final ExternalUserService externalUserService;

    public GatewayLoginController(ExternalUserService externalUserService) {
        this.externalUserService = externalUserService;
    }

    @GetMapping("/authenticate")
    public Mono<TokenInfoResponseDto> login(@RegisteredOAuth2AuthorizedClient("floofle-gateway") OAuth2AuthorizedClient client,
                                            @AuthenticationPrincipal OidcUser user ) {

        String jwtToken = client.getAccessToken().getTokenValue();
        log.info("access token received from authorization server with value : {}", jwtToken);

        Mono<UserInfoResponseDto> userResponse = externalUserService.getUser(getAuth(jwtToken));

        log.info("user info received from authorization server with value : {}", userResponse);

        TokenInfoResponseDto tokenInfo = TokenInfoResponseDto.builder()
                .accessToken(client.getAccessToken().getTokenValue())
                .refreshToken(Objects.requireNonNull(client.getRefreshToken())
                        .getTokenValue())
                .accessTokenExpireAt(client.getAccessToken().getExpiresAt())
                .authorities(extractAuthority(user))
                .build();

        return Mono.just(tokenInfo)
                .zipWith(userResponse)
                .map(tuple -> {
                    TokenInfoResponseDto tokenInfoResponseDto = tuple.getT1();
                    UserInfoResponseDto userInfoResponseDto = tuple.getT2();
                    tokenInfoResponseDto.setUserInfo(userInfoResponseDto);
                    return tokenInfoResponseDto;
                })
                .log();
    }
}
