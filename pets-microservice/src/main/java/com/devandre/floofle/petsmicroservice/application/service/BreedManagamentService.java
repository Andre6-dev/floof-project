package com.devandre.floofle.petsmicroservice.application.service;

import com.devandre.floofle.petsmicroservice.application.mapper.BreedDtoMapper;
import com.devandre.floofle.petsmicroservice.application.usecases.BreedService;
import com.devandre.floofle.petsmicroservice.domain.model.dto.response.BreedDtoResponse;
import com.devandre.floofle.petsmicroservice.domain.port.BreedPersistencePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Andre on 26/03/2024
 * @project Floof Project
 */
@Service
@Slf4j
public class BreedManagamentService implements BreedService {

    private final BreedPersistencePort breedPersistencePort;

    private final BreedDtoMapper breedDtoMapper;

    public BreedManagamentService(BreedPersistencePort breedPersistencePort, BreedDtoMapper breedDtoMapper) {
        this.breedPersistencePort = breedPersistencePort;
        this.breedDtoMapper = breedDtoMapper;
    }

    @Override
    public Mono<List<BreedDtoResponse>> getAllBreeds() {
        log.info("Getting all breeds from the database");
        return breedPersistencePort.getAllBreeds()
                .map(breedDtoMapper::toDto)
                .collectList();
    }
}
