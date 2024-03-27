package com.devandre.floofle.gatewayserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andre on 27/03/2024
 * @project Floof Project
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenInfoResponseDto {

    private UserInfoResponseDto userInfo;
    private String accessToken;
    private String refreshToken;
    private Instant accessTokenExpireAt;
    private List<String> authorities = new ArrayList<>();
}
