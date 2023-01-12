package hu.nye.national_library_system.controller;

import static hu.nye.national_library_system.Constants.*;

import hu.nye.national_library_system.validation.ValidationError;
import hu.nye.national_library_system.validation.ValidationException;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class NLSController {

     protected final Logger logger;

    public NLSController(Logger logger) {
        this.logger = logger;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    protected List<ValidationError> handleValidationException(ValidationException exception) {
        exception.getInvalids().forEach(invalid -> logger.warn(LOGGER_FORMAT, exception.getMessage(),
                invalid.getFieldName(), invalid.getMessage(), invalid.getValue()));
        return exception.getInvalids();
    }
}
