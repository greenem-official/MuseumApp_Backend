package org.daylight.museumbackend.controller;

import lombok.RequiredArgsConstructor;
import org.daylight.museumbackend.dto.PagedResult;
import org.daylight.museumbackend.model.Author;
import org.daylight.museumbackend.model.User;
import org.daylight.museumbackend.repository.AuthorRepository;
import org.daylight.museumbackend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PreAuthorize("hasRole('VISITOR') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    @PostMapping
    public PagedResult<User> getAll(@AuthenticationPrincipal UserDetails user) {
        Page<User> page = userRepository.findAll(PageRequest.of(0, 10));
        return new PagedResult<>(page);
    }
}
