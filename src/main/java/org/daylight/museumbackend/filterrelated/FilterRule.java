package org.daylight.museumbackend.filterrelated;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.daylight.museumbackend.filterrelated.specific.ContainsFilter;
import org.daylight.museumbackend.filterrelated.specific.EqualsFilter;
import org.daylight.museumbackend.filterrelated.specific.RangeFilter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RangeFilter.class, name = "range"),
        @JsonSubTypes.Type(value = EqualsFilter.class, name = "equals"),
        @JsonSubTypes.Type(value = ContainsFilter.class, name = "contains"),
//        @JsonSubTypes.Type(value = GreaterFilter.class, name = "gt"),
//        @JsonSubTypes.Type(value = LessFilter.class, name = "lt")
})
public interface FilterRule {
    String getField();
}
