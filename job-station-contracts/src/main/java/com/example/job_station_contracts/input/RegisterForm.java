package com.example.job_station_contracts.input;

import org.hibernate.validator.constraints.Length;

import com.example.job_station_contracts.interfaces.PasswordDto;
import com.example.job_station_contracts.validation.PasswordMatches;

import jakarta.validation.constraints.NotBlank;

@PasswordMatches()
public class RegisterForm implements PasswordDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String login;
    private String password;
    private String confirmPassword;

    public RegisterForm(String firstName, String middleName, String lastName, String login, String password, String confirmPassword) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Length(min = 2, message = "Имя должно содержать как минимум 2 символа")
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Length(min = 2, message = "Фамилия должна содержать как минимум 2 символа")
    public String getLastName() {
        return lastName;
    }

    @Length(min = 4, message = "Логин должен содержать как минимум 4 символа")
    public String getLogin() {
        return login;
    }

    @Length(min = 8, message = "Пароль должен содержать как минимум 8 символов")
    public String getPassword() {
        return password;
    }

    @NotBlank(message = "Это поле должно быть заполнено")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
