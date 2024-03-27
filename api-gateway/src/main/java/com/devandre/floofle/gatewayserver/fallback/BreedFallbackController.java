package com.devandre.floofle.gatewayserver.fallback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Andre on 27/03/2024
 * @project Floof Project
 */
@RestController
@RequestMapping("/pets-service-fallback")
public class BreedFallbackController {

    @GetMapping()
    public Mono<String> breedFallbackService() {
        return Mono.just("<div style='color:red;' >breed service is unavailable !!</div>");
    }
}
