package hu.nye.national_library_system.validation;

import static hu.nye.national_library_system.Constants.INVALID_MESSAGE;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends Exception {

    private final transient List<ValidationError> invalids;

    public ValidationException(List<ValidationError> invalids, String typeName) {
        super(String.format(INVALID_MESSAGE, typeName));
        this.invalids = invalids;
    }
}
