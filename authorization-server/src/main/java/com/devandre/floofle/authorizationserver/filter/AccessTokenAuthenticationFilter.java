package com.devandre.floofle.authorizationserver.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Andre on 25/03/2024
 * @project Floof Project
 */
@Component
@Slf4j
public class AccessTokenAuthenticationFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX="Bearer ";

    private AuthenticationManager authenticationManager;

    public AccessTokenAuthenticationFilter(
            @Qualifier("bearerTokenAuthenticationManager") AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/auth/users")) {
            String auth = request.getHeader("Authorization");


            if (auth!=null && auth.startsWith(BEARER_PREFIX)){
                String token = auth.replace(BEARER_PREFIX, "");
                BearerTokenAuthenticationToken bearerToken = new BearerTokenAuthenticationToken(token);

                try {
                    Authentication authenticate = authenticationManager.authenticate(bearerToken);
                    SecurityContextHolder.getContext().setAuthentication(authenticate);

                    filterChain.doFilter(request, response);
                } catch (Exception e) {

                    log.error("error on decode token");
                    response.getOutputStream().write("invalid token".getBytes());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
            else {
                response.getOutputStream().write("invalid token !!!".getBytes());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }

        } else filterChain.doFilter(request, response);


    }
}
