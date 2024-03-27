package com.devandre.floofle.petsmicroservice.infrastructure.rest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andre on 25/03/2024
 * @project Floof Project
 */
@RestController
@RequestMapping("/api/v1/pets")
public class TestController {

    @GetMapping("/test")
    public String test(Authentication authentication) {
        return "Welcome to Floofle Pets Microservice! " + authentication.getName() + " is authenticated with credentials: " + authentication.getCredentials() + " and authorities: " + authentication.getAuthorities();
    }
}