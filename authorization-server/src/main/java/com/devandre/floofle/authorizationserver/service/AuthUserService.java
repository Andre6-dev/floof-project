package com.devandre.floofle.authorizationserver.service;

import com.devandre.floofle.authorizationserver.dto.CreateUserDto;
import com.devandre.floofle.authorizationserver.dto.MessageDto;
import com.devandre.floofle.authorizationserver.dto.UserResponseDto;
import com.devandre.floofle.authorizationserver.entity.Role;
import com.devandre.floofle.authorizationserver.entity.User;
import com.devandre.floofle.authorizationserver.entity.enums.RoleName;
import com.devandre.floofle.authorizationserver.repository.RoleRepository;
import com.devandre.floofle.authorizationserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Andre on 11/03/2024
 * @project Floof Project
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public MessageDto createUser(CreateUserDto dto) {
        log.info(
                "Creating user: {}",
                dto
        );
        User user = User.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .build();
        Set<Role> roles = new HashSet<>();
        dto.roles()
                .forEach(role -> {
                    Role r = roleRepository.findByRole(RoleName.valueOf(role))
                            .orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(r);
                });
        user.setRoles(roles);
        userRepository.save(user);
        return new MessageDto("User created successfully");
    }

    public UserResponseDto getUserByUserName(String name) {

        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();

    }
}
