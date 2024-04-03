package com.devandre.floofle.authorizationserver.controller;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Andre on 31/03/2024
 * @project Floof Project
 */
@Controller
public class TestController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/logout")
//    public String logout() {
//        return "logout";
//    }
//
//    @PostMapping("/logout")
//    public String logoutOk(HttpSecurity http) throws Exception {
//        http.logout(
//                logout -> logout
//                        .deleteCookies("JSESSIONID")
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//        );
//        return "dashboard";
//    }


}
