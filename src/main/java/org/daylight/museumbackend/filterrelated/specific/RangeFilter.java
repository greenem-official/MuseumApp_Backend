package org.daylight.museumbackend.filterrelated.specific;

import lombok.Getter;
import org.daylight.museumbackend.filterrelated.FilterRule;

@Getter
public class RangeFilter implements FilterRule {
    private String field;
    private Object from;
    private Object to;
}
