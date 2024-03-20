package com.devandre.floofle.authorizationserver.dto;

import java.util.List;

/**
 * @author Andre on 08/03/2024
 * @project Floof Project
 */
public record CreateUserDto(
        String username,
        String password,
        List<String> roles
) {
}
