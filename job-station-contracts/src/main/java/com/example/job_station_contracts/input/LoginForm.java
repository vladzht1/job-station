package com.example.job_station_contracts.input;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class LoginForm {
    private String identifier;
    private String password;

    public LoginForm(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }

    @NotBlank(message = "Это поле должно быть заполнено")
    public String getIdentifier() {
        return identifier;
    }

    @NotBlank(message = "Это поле должно быть заполнено")
    @Length(min = 8, message = "Пароль должен содержать не менее 8 символов")
    public String getPassword() {
        return password;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
