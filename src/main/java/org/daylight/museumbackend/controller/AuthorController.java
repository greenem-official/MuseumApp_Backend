package org.daylight.museumbackend.controller;

import lombok.RequiredArgsConstructor;
import org.daylight.museumbackend.dto.PagedResult;
import org.daylight.museumbackend.model.Author;
import org.daylight.museumbackend.model.Hall;
import org.daylight.museumbackend.repository.AuthorRepository;
import org.daylight.museumbackend.repository.HallRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;

    @PreAuthorize("hasRole('VISITOR')")
    @PostMapping
    public PagedResult<Author> getAll(@AuthenticationPrincipal UserDetails user) {
        Page<Author> page = authorRepository.findAll(PageRequest.of(0, 10));
        return new PagedResult<>(page);
    }
}
