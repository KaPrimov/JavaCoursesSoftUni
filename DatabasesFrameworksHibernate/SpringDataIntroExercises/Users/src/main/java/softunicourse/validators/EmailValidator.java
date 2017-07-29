package softunicourse.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {
    @Override
    public void initialize(Email email) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (!email.matches("^[A-Za-z][A-Za-z.-_]*[A-Za-z]@\\w+\\..+[A-Za-z]$")) {
            return false;
        }
        return true;
    }
}
