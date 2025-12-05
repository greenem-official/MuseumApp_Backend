package org.daylight.museumbackend.filterrelated;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.daylight.museumbackend.filterrelated.specific.ContainsFilter;
import org.daylight.museumbackend.filterrelated.specific.EqualsFilter;
import org.daylight.museumbackend.filterrelated.specific.RangeFilter;
import org.daylight.museumbackend.model.Item;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemSpecificationBuilder {
    public static Specification<Item> build(PagedRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (FilterRule rule : request.getFilters()) {
                String field = ItemFieldMapper.map(rule.getField());
                if (field == null) continue;

                Path<?> path = resolvePath(root, field);

                if (rule instanceof RangeFilter f) {
                    Class<?> javaType = path.getJavaType();

                    if (Comparable.class.isAssignableFrom(javaType)) {
                        if (f.getMin() != null) {
                            predicates.add(cb.greaterThanOrEqualTo(
                                    path.as((Class<? extends Comparable>) javaType),
                                    (Comparable) convert(f.getMin(), javaType)
                            ));
                        }

                        if (f.getMax() != null) {
                            predicates.add(cb.lessThanOrEqualTo(
                                    path.as((Class<? extends Comparable>) javaType),
                                    (Comparable) convert(f.getMax(), javaType)
                            ));
                        }
                    }
                }

                if (rule instanceof EqualsFilter f) {
                    predicates.add(cb.equal(path, f.getValue()));
                }

                if (rule instanceof ContainsFilter f) {
                    predicates.add(cb.like(cb.lower(path.as(String.class)),
                            "%" + f.getValue().toLowerCase() + "%"));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static Object convert(Object value, Class<?> targetType) {
        if (value == null) return null;

        if (targetType.equals(Integer.class)) return Integer.valueOf(value.toString());
        if (targetType.equals(Long.class)) return Long.valueOf(value.toString());
        if (targetType.equals(Double.class)) return Double.valueOf(value.toString());
        if (targetType.equals(LocalDate.class)) return LocalDate.parse(value.toString());

        return value;
    }

    private static Path<?> resolvePath(Root<?> root, String path) {
        Path<?> p = root;
        for (String part : path.split("\\.")) {
            p = p.get(part);
        }
        return p;
    }
}
