package com.devandre.floofle.authorizationserver.controller;

import com.devandre.floofle.authorizationserver.dto.CreateUserDto;
import com.devandre.floofle.authorizationserver.dto.MessageDto;
import com.devandre.floofle.authorizationserver.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andre on 11/03/2024
 * @project Floof Project
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUserService authUserService;

    @PostMapping("/create")
    public ResponseEntity<MessageDto> createUser(@RequestBody CreateUserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authUserService.createUser(dto));
    }
}
