package hu.nye.national_library_system.filter;

import hu.nye.national_library_system.etc.QueryOperator;

import javax.persistence.criteria.*;
import java.time.LocalTime;

public class TimeFilter extends Filter<LocalTime> {

    public TimeFilter(String fieldName, QueryOperator queryOperator, LocalTime value) {
        super(fieldName, queryOperator, value);
    }

    @Override
    public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root<?> root, String fieldName) {
        Predicate fieldPredicate = null;
        Path<LocalTime> fieldPath = root.get(fieldName);
        switch (queryOperator) {
            case EQUALS:
                fieldPredicate = criteriaBuilder.equal(fieldPath, value);
                break;
            case LESS_THAN_OR_EQUALS:
                fieldPredicate = criteriaBuilder.lessThanOrEqualTo(fieldPath, value);
                break;
            case GREATER_THAN_OR_EQUALS:
                fieldPredicate = criteriaBuilder.greaterThanOrEqualTo(fieldPath, value);
                break;
            default:
                fieldPredicate = null;
                break;
        }
        return fieldPredicate;
    }
}
