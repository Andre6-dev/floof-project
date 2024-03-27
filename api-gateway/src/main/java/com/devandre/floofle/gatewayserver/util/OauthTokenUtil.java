package com.devandre.floofle.gatewayserver.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.stream.Collectors;

import static com.devandre.floofle.gatewayserver.constant.ConstantField.TOKEN_PREFIX;

/**
 * @author Andre on 27/03/2024
 * @project Floof Project
 */
public class OauthTokenUtil {

    public static List<String> extractAuthority(OAuth2User oAuth2User) {
        // log the authorities
        return oAuth2User.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public static String getAuth(String token){
        return TOKEN_PREFIX.concat(token);
    }
}
