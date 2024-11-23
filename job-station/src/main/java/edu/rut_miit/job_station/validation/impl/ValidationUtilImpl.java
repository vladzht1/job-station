package edu.rut_miit.job_station.validation.impl;

import java.util.Set;

import org.springframework.stereotype.Component;

import edu.rut_miit.job_station.validation.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class ValidationUtilImpl implements ValidationUtil {
    private Validator validator;

    public ValidationUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E object) {
        return this.validator.validate(object).size() == 0;
    }

    @Override
    public <E> Set<ConstraintViolation<E>> violations(E object) {
        return this.validator.validate(object);
    }
}
