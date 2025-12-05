package org.daylight.museumbackend.filterrelated.specific;

import lombok.Getter;
import org.daylight.museumbackend.filterrelated.FilterRule;

@Getter
public class StartsWithFilter implements FilterRule {
    private String field;
    private String value;
}
