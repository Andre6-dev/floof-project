package com.devandre.floofle.petsmicroservice.domain.port;

import com.devandre.floofle.petsmicroservice.domain.model.Breed;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Andre on 26/03/2024
 * @project Floof Project
 */

public interface BreedPersistencePort {

    Flux<Breed> getAllBreeds();
}
