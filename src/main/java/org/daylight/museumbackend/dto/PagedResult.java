package org.daylight.museumbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PagedResult<T> {
    private List<T> items;
    private int page;
    private int size;
    private final long totalItems;
    private final int totalPages;

    public PagedResult(Page<T> page) {
        this.items = page.getContent();
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalItems = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}