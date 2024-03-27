package com.devandre.floofle.petsmicroservice.infrastructure.adapter.entity;

import com.devandre.floofle.petsmicroservice.infrastructure.adapter.entity.shared.AuditTable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @author Andre on 22/03/2024
 * @project Floof Project
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "pets", schema = "floof_pet")
public class PetEntity extends AuditTable implements Persistable<Long> {

    @Id
    private Long id;

    @Column("name")
    private String name;

    private String description;

    @Column("birth_date")
    private LocalDateTime birthDate;

    private Integer age;

    private Double weight;

    private String sex;

    @Column("url_image")
    private String urlImage;

    @Column("base_color")
    private String baseColor;

    @Column("is_for_adoption")
    private Boolean isForAdoption;

    @Column("is_for_sale")
    private Boolean isForSale;

    @Column("type_id")
    private Long typeId;

    @Column("user_id")
    private Long userId;

    @Column("breed_id")
    private Long breedId;

    @Column("entry_reason_id")
    private Long entryReasonId;

    @Transient
    private PetTypeEntity type;

    @Transient
    private BreedEntity breed;

    @Transient
    private EntryReasonEntity entryReason;

    @Override
    public Long getId() {
        return id == null ? null : id;
    }

    @Override
    public boolean isNew() {
        return id == null || id == 0;
    }
}
