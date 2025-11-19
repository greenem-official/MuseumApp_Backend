package org.daylight.museumbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublicStatsDto {
    private long totalItems;
    private long totalCollections;
    private long totalHalls;
    private long totalAuthors;
}
