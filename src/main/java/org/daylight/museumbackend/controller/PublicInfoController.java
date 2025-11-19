package org.daylight.museumbackend.controller;

import lombok.RequiredArgsConstructor;
import org.daylight.museumbackend.model.Item;
import org.daylight.museumbackend.repository.ItemRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicInfoController {
    private final ItemRepository itemRepository;

    @PreAuthorize("hasRole('ANONYMOUS') or hasRole('VISITOR')")
    @GetMapping("/info")
    public String publicInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("AUTH IN CONTROLLER:");
        System.out.println("Principal: " + auth.getPrincipal());
        System.out.println("Authorities: " + auth.getAuthorities());
        System.out.println("Authenticated: " + auth.isAuthenticated());
        return "A";
    }
}
