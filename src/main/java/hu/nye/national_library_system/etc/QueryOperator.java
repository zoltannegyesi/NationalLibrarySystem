package hu.nye.national_library_system.etc;

import lombok.Getter;

@Getter
public enum QueryOperator {

    GREATER_THAN_OR_EQUALS(">="),
    LESS_THAN_OR_EQUALS("<="),
    LIKE("~="),
    EQUALS("=");

    String operator;

    QueryOperator(String operator) {
        this.operator = operator;
    }
}
