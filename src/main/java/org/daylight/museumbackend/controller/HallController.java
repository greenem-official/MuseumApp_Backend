package org.daylight.museumbackend.controller;

import lombok.RequiredArgsConstructor;
import org.daylight.museumbackend.dto.PagedResult;
import org.daylight.museumbackend.model.Collection;
import org.daylight.museumbackend.model.Hall;
import org.daylight.museumbackend.repository.CollectionRepository;
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
@RequestMapping("/halls")
@RequiredArgsConstructor
public class HallController {
    private final HallRepository hallRepository;

    @PreAuthorize("hasRole('VISITOR') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    @PostMapping
    public PagedResult<Hall> getAll(@AuthenticationPrincipal UserDetails user) {
        Page<Hall> page = hallRepository.findAll(PageRequest.of(0, 10));
        return new PagedResult<>(page);
    }
}
