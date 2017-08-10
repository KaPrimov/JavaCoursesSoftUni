package softuni.photography.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

@Component
public class DecimalDigitsValidator implements ConstraintValidator<DecimalDigitsValidation, BigDecimal> {

    private int fraction;

    @Override
    public void initialize(DecimalDigitsValidation constraintAnnotation) {
        this.fraction = constraintAnnotation.fraction();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {

        String number = value.toString();
        String regex = String.format("^\\d+[.,]\\d{%d}$", this.fraction);

        return number.matches(regex);
    }
}
