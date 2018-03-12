package com.softuni.residentEvil.annotations;

import com.softuni.residentEvil.entities.enums.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreAuthenticate {
    boolean loggedIn() default false;
    RoleEnum inRole() default RoleEnum.USER;
}
