package com.devandre.floofle.petsmicroservice.infrastructure.adapter.entity.shared;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

/**
 * @author Andre on 22/03/2024
 * @project Floof Project
 */
@Getter
@Setter
public abstract class AuditTable {

    @Column("created_at")
    @CreatedBy
    private LocalDateTime createdAt;

    @Column("updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
