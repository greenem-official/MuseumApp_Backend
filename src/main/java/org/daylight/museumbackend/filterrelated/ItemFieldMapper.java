package org.daylight.museumbackend.filterrelated;

import java.util.Map;

public class ItemFieldMapper {
    private static final Map<String, String> FIELD_MAP = Map.of(
            "name", "name",
            "year", "year",
            "condition", "condition",
            "author", "author.name",
            "hall", "hall.name",
            "item", "item.name"
    );

    public static String map(String clientField) {
        return FIELD_MAP.get(clientField);
    }
}
