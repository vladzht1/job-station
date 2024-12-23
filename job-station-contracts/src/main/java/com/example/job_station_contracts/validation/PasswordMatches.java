package com.example.job_station_contracts.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.job_station_contracts.validation.impl.PasswordMatchesValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PasswordMatchesValidator.class)
public @interface PasswordMatches {
    String message() default "Пароли должны совпадать";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
