package edu.rut_miit.job_station.config;

import org.springframework.context.annotation.Configuration;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

@Configuration
public class ApplicationBeanConfig {

    public Validator validator() {
        return Validation
            .buildDefaultValidatorFactory()
            .getValidator();
    }
}
