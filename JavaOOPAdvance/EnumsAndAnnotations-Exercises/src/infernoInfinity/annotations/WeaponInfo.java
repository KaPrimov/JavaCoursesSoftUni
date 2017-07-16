package infernoInfinity.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WeaponInfo {
    String author() default "Unknown Author";
    int revision() default 0;
    String description() default "No description available";
    String[] reviewers() default {};
}
