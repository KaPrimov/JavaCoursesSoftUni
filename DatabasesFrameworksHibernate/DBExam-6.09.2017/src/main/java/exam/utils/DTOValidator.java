package exam.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class DTOValidator {

    private static Validator VALIDATOR = null;

    private DTOValidator() {
    }

    private static Validator getValidator() {
        if (VALIDATOR == null) {
            VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
        }
        return VALIDATOR;
    }

    public static <T> boolean isValid(T t) {
        Set<ConstraintViolation<T>> errors = getValidator().validate(t);
        return errors.size() == 0;
    }
}
