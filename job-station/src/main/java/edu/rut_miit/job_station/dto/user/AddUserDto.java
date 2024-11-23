package edu.rut_miit.job_station.dto.user;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddUserDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String login;
    private String password;

    public AddUserDto(String firstName, String middleName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    @NotNull(message = "Необходимо указать имя")
    @NotEmpty(message = "Имя не может быть пустым")
    @Length(min = 2, message = "Имя должно содержать как минимум 2 символа")
    public String getFirstName() {
        return firstName;
    }

    @Length(min = 2, message = "Отчество должно содержать как минимум 2 символа")
    public String getMiddleName() {
        return middleName;
    }

    @NotNull(message = "Необходимо указать фамилию")
    @NotEmpty(message = "Фамилия не может быть пустой")
    @Length(min = 2, message = "Фамилия должна содержать как минимум 2 символа")
    public String getLastName() {
        return lastName;
    }

    @NotNull(message = "Необходимо указать логин")
    @NotEmpty(message = "Логин не может быть пустым")
    @Length(min = 4, message = "Логин должен содержать как минимум 4 символа")
    public String getLogin() {
        return login;
    }

    @NotNull(message = "Необходимо указать пароль")
    @NotEmpty(message = "Пароль не может быть пустым")
    @Length(min = 8, message = "Пароль должен содержать как минимум 8 символов")
    public String getPassword() {
        return password;
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
}
