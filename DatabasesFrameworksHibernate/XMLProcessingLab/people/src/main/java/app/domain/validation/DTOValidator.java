package app.domain.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by User on 1.8.2017 г..
 */
@Component
public class DTOValidator {

    private Validator validator;

    public DTOValidator () {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <T> boolean isValid (T t) {
        Set<ConstraintViolation<T>> errors = validator.validate(t);
        return errors.size() == 0;
    }
}
