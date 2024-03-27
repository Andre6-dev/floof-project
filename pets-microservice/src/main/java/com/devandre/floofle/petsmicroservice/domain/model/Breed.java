package com.devandre.floofle.petsmicroservice.domain.model;

import lombok.*;

/**
 * @author Andre on 26/03/2024
 * @project Floof Project
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Breed {

    private Long id;

    private String name;

    private String iconName;
}
