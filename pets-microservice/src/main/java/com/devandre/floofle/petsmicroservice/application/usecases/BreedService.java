package com.devandre.floofle.petsmicroservice.application.usecases;

import com.devandre.floofle.petsmicroservice.domain.model.dto.response.BreedDtoResponse;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Andre on 26/03/2024
 * @project Floof Project
 */
public interface BreedService {

    Mono<List<BreedDtoResponse>> getAllBreeds();
}
