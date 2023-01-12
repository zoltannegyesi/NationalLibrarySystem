package hu.nye.national_library_system.filter;

import hu.nye.national_library_system.etc.QueryOperator;

import javax.persistence.criteria.*;

public class BooleanFilter extends Filter<Boolean> {

    public BooleanFilter(String fieldName, QueryOperator queryOperator, Boolean value) {
        super(fieldName, queryOperator, value);
    }

    @Override
    public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root<?> root, String fieldName) {
        Predicate fieldPredicate = null;
        Path<Boolean> fieldPath = root.get(fieldName);
        switch (queryOperator) {
            case EQUALS:
                fieldPredicate = criteriaBuilder.equal(fieldPath, value);
                break;
            default:
                fieldPredicate = null;
                break;
        }
        return fieldPredicate;
    }
}
