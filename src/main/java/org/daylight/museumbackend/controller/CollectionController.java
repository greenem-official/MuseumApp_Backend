package org.daylight.museumbackend.controller;

import lombok.RequiredArgsConstructor;
import org.daylight.museumbackend.dto.PagedResult;
import org.daylight.museumbackend.model.Collection;
import org.daylight.museumbackend.model.Item;
import org.daylight.museumbackend.repository.CollectionRepository;
import org.daylight.museumbackend.repository.ItemRepository;
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
@RequestMapping("/collections")
@RequiredArgsConstructor
public class CollectionController {
    private final CollectionRepository collectionRepository;

    @PreAuthorize("hasRole('VISITOR')")
    @PostMapping
    public PagedResult<Collection> getAll(@AuthenticationPrincipal UserDetails user) {
        Page<Collection> page = collectionRepository.findAll(PageRequest.of(0, 10));
        return new PagedResult<>(page);
    }
}
