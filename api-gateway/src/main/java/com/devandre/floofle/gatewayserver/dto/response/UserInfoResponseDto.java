package com.devandre.floofle.gatewayserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Andre on 27/03/2024
 * @project Floof Project
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDto {

    private Long id;
    private String username;
    private String email;
}
