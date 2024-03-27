package com.devandre.floofle.petsmicroservice.infrastructure.adapter.mapper;

import com.devandre.floofle.petsmicroservice.domain.model.Breed;
import com.devandre.floofle.petsmicroservice.infrastructure.adapter.entity.BreedEntity;
import org.springframework.stereotype.Component;

/**
 * @author Andre on 26/03/2024
 * @project Floof Project
 */
@Component
public class BreedMapper {

    public BreedMapper() {
    }

    public BreedEntity toEntity(Breed domain) {
        return BreedEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .iconName(domain.getIconName())
                .build();
    }

    public Breed toDomain(BreedEntity entity) {
        return Breed.builder()
                .id(entity.getId())
                .name(entity.getName())
                .iconName(entity.getIconName())
                .build();
    }
}
