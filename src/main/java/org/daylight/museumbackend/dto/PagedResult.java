package org.daylight.museumbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PagedResult<T> {
    private List<T> items;
    private int page;
    private int totalPages;
    private long totalElements;

    public PagedResult(Page<T> page) {
        this.items = page.getContent();
        this.page = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}