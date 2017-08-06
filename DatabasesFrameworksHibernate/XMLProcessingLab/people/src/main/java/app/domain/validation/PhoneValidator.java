package app.domain.validation;

import app.domain.model.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by User on 30.7.2017 г..
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    public void initialize(Phone constraint) {
        System.out.println("PhoneValidator created @@@@@@@@@@@@@@@@@@22");
    }

    public PhoneValidator() {
        System.out.println("PhoneValidator created @@@@@@@@@@@@@@@@@@22");
    }

    public boolean isValid(String obj, ConstraintValidatorContext context) {
        return obj != null && obj.matches("[0-9]{8,10}");
    }
}
