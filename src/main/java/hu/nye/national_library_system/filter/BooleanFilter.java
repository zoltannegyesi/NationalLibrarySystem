package hu.nye.national_library_system.filter;

import hu.nye.national_library_system.Constants;
import hu.nye.national_library_system.etc.QueryOperator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BooleanFilter extends Filter<Boolean> {

    public BooleanFilter(String fieldName, QueryOperator queryOperator, Boolean value) {
        super(fieldName, queryOperator, value);
    }

    @Override
    public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root<?> root, String fieldName) {
        Predicate fieldPredicate = null;
        switch (queryOperator) {
            case EQUALS:
                fieldPredicate = criteriaBuilder.equal(root.get(fieldName), value);
                break;
            default:
                fieldPredicate = null;
                break;
        }
        return fieldPredicate;
    }
}
