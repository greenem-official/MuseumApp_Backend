package org.daylight.museumbackend.util;

import org.daylight.museumbackend.filterrelated.ItemFieldMapper;
import org.daylight.museumbackend.filterrelated.PagedRequest;
import org.springframework.data.domain.Sort;

public class ControllerUtil {
    public static Sort getSort(PagedRequest request) {
        Sort sort = Sort.unsorted();
        if (request.getSort() != null) {
            String field = ItemFieldMapper.map(request.getSort().getField());
            if (field != null) {
                sort = Sort.by(
                        "asc".equalsIgnoreCase(request.getSort().getDir())
                                ? Sort.Direction.ASC
                                : Sort.Direction.DESC,
                        field
                );
            }
        }
        return sort;
    }
}
