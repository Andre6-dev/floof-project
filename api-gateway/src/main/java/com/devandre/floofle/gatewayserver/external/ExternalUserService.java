package com.devandre.floofle.gatewayserver.external;

import com.devandre.floofle.gatewayserver.dto.response.UserInfoResponseDto;
import reactor.core.publisher.Mono;

/**
 * @author Andre on 27/03/2024
 * @project Floof Project
 */
public interface ExternalUserService {

    Mono<UserInfoResponseDto> getUser(String auth);
}
