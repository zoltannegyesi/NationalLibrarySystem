package hu.nye.national_library_system.filter;

import hu.nye.national_library_system.Constants;
import hu.nye.national_library_system.etc.QueryOperator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LongStringArrayFilter extends Filter<String> {

    public LongStringArrayFilter(String fieldName, QueryOperator queryOperator, String value) {
        super(fieldName, queryOperator, value);
    }

    @Override
    public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root<?> root, String fieldName) {
        Join<Object, Object> arrayField = root.join(fieldName);
        Predicate fieldPredicate = null;
        switch (queryOperator) {
            case EQUALS:
                //TODO: bekérni paraméterként a field namet (a book/library arrayFieldjét, amire szűrnénk...) is: arrayField.get(fieldName)
                fieldPredicate = criteriaBuilder.equal(arrayField.get(Constants.VALUE), value);
                break;
            case LIKE:
                fieldPredicate = criteriaBuilder.like(arrayField.get(Constants.VALUE), "%" + value + "%");
                break;
            case LESS_THAN_OR_EQUALS:
                fieldPredicate = criteriaBuilder.lessThanOrEqualTo(arrayField.get(Constants.VALUE), value);
                break;
            case GREATER_THAN_OR_EQUALS:
                fieldPredicate = criteriaBuilder.greaterThanOrEqualTo(arrayField.get(Constants.VALUE), value);
                break;
            default:
                fieldPredicate = null;
                break;
        }
        return fieldPredicate;
    }
}
