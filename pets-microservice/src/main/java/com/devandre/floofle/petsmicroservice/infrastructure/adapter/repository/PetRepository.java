package com.devandre.floofle.petsmicroservice.infrastructure.adapter.repository;

import com.devandre.floofle.petsmicroservice.infrastructure.adapter.entity.PetEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andre on 25/03/2024
 * @project Floof Project
 */
@Repository
public interface PetRepository extends ReactiveCrudRepository<PetEntity, Long > {
}
