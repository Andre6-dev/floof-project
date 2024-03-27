package com.devandre.floofle.petsmicroservice.infrastructure.adapter;

import com.devandre.floofle.petsmicroservice.domain.model.Breed;
import com.devandre.floofle.petsmicroservice.domain.port.BreedPersistencePort;
import com.devandre.floofle.petsmicroservice.infrastructure.adapter.mapper.BreedMapper;
import com.devandre.floofle.petsmicroservice.infrastructure.adapter.repository.BreedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

/**
 * @author Andre on 26/03/2024
 * @project Floof Project
 */
@Service
@Transactional
public class BreedSpringJpaAdapter implements BreedPersistencePort {

    private final BreedRepository breedRepository;

    private final BreedMapper breedMapper;

    public BreedSpringJpaAdapter(BreedRepository breedRepository, BreedMapper breedMapper) {
        this.breedRepository = breedRepository;
        this.breedMapper = breedMapper;
    }

    @Override
    public Flux<Breed> getAllBreeds() {
        // Collect all breeds from the database
        return breedRepository.findAll()
                .map(breedMapper::toDomain);
    }
}
