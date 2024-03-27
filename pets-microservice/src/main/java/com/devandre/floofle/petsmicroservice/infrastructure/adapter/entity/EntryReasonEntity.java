package com.devandre.floofle.petsmicroservice.infrastructure.adapter.entity;

import com.devandre.floofle.petsmicroservice.infrastructure.adapter.entity.shared.AuditTable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Andre on 22/03/2024
 * @project Floof Project
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "entry_reason", schema = "floof_pet")
public class EntryReasonEntity extends AuditTable implements Persistable<Long> {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Override
    public Long getId() {
        return id == null ? null : id;
    }

    @Override
    public boolean isNew() {
        return id == null || id == 0;
    }
}
