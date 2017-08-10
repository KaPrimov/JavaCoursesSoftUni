package softuni.photography.validation;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = DecimalDigitsValidator.class)
public @interface DecimalDigitsValidation {
    String message() default "Invalid decimal number format";
    int fraction() default 1;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
