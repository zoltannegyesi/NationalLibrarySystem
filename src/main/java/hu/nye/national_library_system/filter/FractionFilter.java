package hu.nye.national_library_system.filter;

import hu.nye.national_library_system.Constants;
import hu.nye.national_library_system.etc.QueryOperator;

import javax.persistence.criteria.*;
import java.math.BigDecimal;

public class FractionFilter extends Filter<BigDecimal> {

    public FractionFilter(String fieldName, QueryOperator queryOperator, BigDecimal value) {
        super(fieldName, queryOperator, value);
    }

    @Override
    public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root<?> root, String fieldName) {
        Predicate fieldPredicate = null;
        Path<BigDecimal> fieldPath = root.get(fieldName);
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
