package com.devandre.floofle.petsmicroservice.application.mapper;

import com.devandre.floofle.petsmicroservice.domain.model.Breed;
import com.devandre.floofle.petsmicroservice.domain.model.dto.response.BreedDtoResponse;
import org.springframework.stereotype.Component;

/**
 * @author Andre on 26/03/2024
 * @project Floof Project
 */
@Component
public class BreedDtoMapper {

    public BreedDtoMapper() {
    }

    public BreedDtoResponse toDto(Breed domain) {
        return BreedDtoResponse.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }

    public Breed toDomain(BreedDtoResponse dto) {
        return Breed.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
