package hu.nye.national_library_system.filter;

import hu.nye.national_library_system.etc.QueryOperator;

import javax.persistence.criteria.*;

public class NumberFilter extends Filter<Long> {

    public NumberFilter(String fieldName, QueryOperator queryOperator, Long value) {
        super(fieldName, queryOperator, value);
    }

    @Override
    public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root<?> root, String fieldName) {
        Predicate fieldPredicate = null;
        Path<Long> fieldPath = root.get(fieldName);
        switch (queryOperator) {
            case EQUALS:
                fieldPredicate = criteriaBuilder.equal(fieldPath, value);
                break;
            case LESS_THAN_OR_EQUALS:
                fieldPredicate = criteriaBuilder.le(fieldPath, value);
                break;
            case GREATER_THAN_OR_EQUALS:
                fieldPredicate = criteriaBuilder.ge(fieldPath, value);
                break;
            default:
                fieldPredicate = null;
                break;
        }
        return fieldPredicate;
    }
}
