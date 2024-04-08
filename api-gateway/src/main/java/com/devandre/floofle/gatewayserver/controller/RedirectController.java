package com.devandre.floofle.gatewayserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.RedirectView;
import reactor.core.publisher.Mono;

/**
 * @author Andre on 08/04/2024
 * @project Floof Project
 */
@Controller
public class RedirectController {

    @GetMapping("/loginSuccess")
    public Mono<RedirectView> loginSuccess() {
        return Mono.just(new RedirectView("/dashboard"));
    }

    @GetMapping("/logged-out")
    public Mono<RedirectView> logout() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/landing");
        return Mono.just(redirectView);
    }
}
