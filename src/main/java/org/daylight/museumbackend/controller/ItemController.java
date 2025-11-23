package org.daylight.museumbackend.controller;

import lombok.RequiredArgsConstructor;
import org.daylight.museumbackend.dto.PagedResult;
import org.daylight.museumbackend.model.Item;
import org.daylight.museumbackend.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @PreAuthorize("hasRole('VISITOR')")
    @GetMapping
    public PagedResult<Item> getAll(@AuthenticationPrincipal UserDetails user) {
        Page<Item> page = itemRepository.findAll(PageRequest.of(0, 10));
//        for
        System.out.println("itemRepository size: " + itemRepository.count());
        return new PagedResult<>(page);
    }
}
