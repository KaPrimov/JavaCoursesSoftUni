package app.gamestore.annotations;

import app.gamestore.annotations.validators.SizeMatcherValidator;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = SizeMatcherValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SizeMatching {
    String message() default "Size is not positive or has more than one decimal sign";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
