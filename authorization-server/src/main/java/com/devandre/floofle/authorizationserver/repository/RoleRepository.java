package com.devandre.floofle.authorizationserver.repository;

import com.devandre.floofle.authorizationserver.entity.Role;
import com.devandre.floofle.authorizationserver.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(RoleName role);
}