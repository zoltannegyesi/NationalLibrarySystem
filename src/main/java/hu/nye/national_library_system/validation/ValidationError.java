package hu.nye.national_library_system.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {

    private String fieldName;
    private String message;
    private Object value;
}
