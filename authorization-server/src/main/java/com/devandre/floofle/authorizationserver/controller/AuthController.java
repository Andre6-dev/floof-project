package com.devandre.floofle.authorizationserver.controller;

import com.devandre.floofle.authorizationserver.dto.CreateUserDto;
import com.devandre.floofle.authorizationserver.dto.MessageDto;
import com.devandre.floofle.authorizationserver.dto.UserResponseDto;
import com.devandre.floofle.authorizationserver.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test LOGOUT API!");
    }

    @GetMapping("/users")
    public ResponseEntity<UserResponseDto> getAuthenticatedUser(Authentication authentication) {
        UserResponseDto user = authUserService.getUserByUserName(authentication.getName());

        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public String logoutOk(HttpSecurity http) throws Exception {
        http.logout(
                logout -> logout
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
        );
        return "login?logout";
    }
}
