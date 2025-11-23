package org.daylight.museumbackend.controller;

import lombok.RequiredArgsConstructor;
import org.daylight.museumbackend.filterrelated.ItemSpecificationBuilder;
import org.daylight.museumbackend.filterrelated.PagedRequest;
import org.daylight.museumbackend.dto.PagedResult;
import org.daylight.museumbackend.model.Item;
import org.daylight.museumbackend.repository.ItemRepository;
import org.daylight.museumbackend.util.ControllerUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @PreAuthorize("hasRole('VISITOR') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    @PostMapping
    public PagedResult<Item> getAll(@AuthenticationPrincipal UserDetails user, @RequestBody PagedRequest request) {
        Specification<Item> spec = ItemSpecificationBuilder.build(request);

        Pageable pageable = PageRequest.of(
                request.getPage(),
                request.getSize(),
                ControllerUtil.getSort(request)
        );

        Page<Item> page = itemRepository.findAll(spec, pageable);
        return new PagedResult<>(page);
    }
}
