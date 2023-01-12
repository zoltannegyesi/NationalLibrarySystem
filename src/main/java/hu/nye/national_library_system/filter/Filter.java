package hu.nye.national_library_system.filter;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
public abstract class Filter<T>{

    protected String fieldName;
    protected QueryOperator queryOperator;
    protected T value;

    protected Filter(String fieldName, QueryOperator queryOperator, T value) {
        this.fieldName = fieldName;
        this.queryOperator = queryOperator;
        this.value = value;
    }

    public abstract Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root<?> root, String fieldName);
}
