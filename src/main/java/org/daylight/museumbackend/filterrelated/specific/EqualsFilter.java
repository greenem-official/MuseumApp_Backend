package org.daylight.museumbackend.filterrelated.specific;

import lombok.Getter;
import org.daylight.museumbackend.filterrelated.FilterRule;

@Getter
public class EqualsFilter implements FilterRule {
    private String field;
    private Object value;
}
