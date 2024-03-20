package com.devandre.floofle.authorizationserver.service;

import com.devandre.floofle.authorizationserver.entity.User;
import com.devandre.floofle.authorizationserver.repository.UserRepository;
import com.devandre.floofle.authorizationserver.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Andre on 08/03/2024
 * @project Floof Project
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by username: {}", username);

        Optional<User> user = userRepository.findByUsername(username);

        return user.map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
