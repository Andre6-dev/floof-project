package com.devandre.floofle.authorizationserver.entity.shared;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * @author Andre on 08/03/2024
 * @project Floof Project
 */
@Getter
@Setter
@MappedSuperclass
public abstract class SharedEntity {

    @Column(name = "created_at", updatable = false)
    @CreatedBy
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "deleted_by")
    private LocalDateTime deletedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
