package com.devandre.floofle.petsmicroservice.infrastructure.rest.controller;

import com.devandre.floofle.petsmicroservice.application.usecases.BreedService;
import com.devandre.floofle.petsmicroservice.infrastructure.rest.controller.handler.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Andre on 26/03/2024
 * @project Floof Project
 */
@RestController
@RequestMapping("/api/v1")
public class BreedController {

    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping("/breeds")
    public Mono<ResponseEntity<Object>> getAllBreeds() {
        return ResponseHandler.generateMonoResponse(HttpStatus.OK,
                breedService.getAllBreeds()
                        .map(Object.class::cast),
                true
        );
    }

}
