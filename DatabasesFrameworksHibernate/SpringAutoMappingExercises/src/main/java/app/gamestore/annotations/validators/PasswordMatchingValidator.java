package app.gamestore.annotations.validators;

import app.gamestore.annotations.PasswordMatching;
import app.gamestore.dto.bindingDtos.user.RegisterUser;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, Object> {

    @Override
    public void initialize(PasswordMatching constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object inst, ConstraintValidatorContext context) {
        if (inst instanceof RegisterUser) {
            RegisterUser user = (RegisterUser) inst;
            return user.getPassword().equals(user.getConfirmPassword());
        }
        return false;
    }
}
