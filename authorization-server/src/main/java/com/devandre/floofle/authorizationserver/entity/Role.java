package com.devandre.floofle.authorizationserver.entity;

import com.devandre.floofle.authorizationserver.entity.enums.RoleName;
import com.devandre.floofle.authorizationserver.entity.shared.SharedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Andre on 08/03/2024
 * @project Floof Project
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(
        name = "roles",
        schema = "floof_auth"
)
public class Role extends SharedEntity implements GrantedAuthority {

    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            schema = "floof_auth",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName role;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
