package com.example.job_station_contracts.validation.impl;

import com.example.job_station_contracts.interfaces.PasswordDto;
import com.example.job_station_contracts.validation.PasswordMatches;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, PasswordDto> {
    @Override
    public boolean isValid(PasswordDto value, ConstraintValidatorContext context) {
        return value.getPassword().trim().equals(value.getConfirmPassword().trim());
    }
}
