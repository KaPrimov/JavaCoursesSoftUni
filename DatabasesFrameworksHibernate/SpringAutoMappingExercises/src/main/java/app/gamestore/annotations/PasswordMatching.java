package app.gamestore.annotations;

import app.gamestore.annotations.validators.PasswordMatchingValidator;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = PasswordMatchingValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatching {
    String message() default "Passwords does not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
