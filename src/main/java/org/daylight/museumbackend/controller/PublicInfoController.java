package org.daylight.museumbackend.controller;

import lombok.RequiredArgsConstructor;
import org.daylight.museumbackend.dto.PublicStatsDto;
import org.daylight.museumbackend.model.Item;
import org.daylight.museumbackend.repository.AuthorRepository;
import org.daylight.museumbackend.repository.CollectionRepository;
import org.daylight.museumbackend.repository.HallRepository;
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
    private final CollectionRepository collectionRepository;
    private final HallRepository hallRepository;
    private final AuthorRepository authorRepository;

    @PreAuthorize("hasRole('ANONYMOUS') or hasRole('VISITOR') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    @GetMapping("/general_stats")
    public PublicStatsDto getGeneralStats() {
        long totalItems = itemRepository.count();
        long totalCollections = collectionRepository.count();
        long totalHalls = hallRepository.count();
        long totalAuthors = authorRepository.count();

        return new PublicStatsDto(totalItems, totalCollections, totalHalls, totalAuthors);
    }
}
