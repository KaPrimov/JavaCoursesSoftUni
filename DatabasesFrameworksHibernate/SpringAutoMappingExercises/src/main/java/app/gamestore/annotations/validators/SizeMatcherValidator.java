package app.gamestore.annotations.validators;

import app.gamestore.annotations.SizeMatching;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SizeMatcherValidator implements ConstraintValidator<SizeMatching, Double> {
    @Override
    public void initialize(SizeMatching constraintAnnotation) {

    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        String strValue = "" + value;
        Pattern pattern = Pattern.compile("^\\d+\\.\\d$");
        Matcher matcher = pattern.matcher(strValue);
        return matcher.find();
    }
}
