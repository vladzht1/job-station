package edu.rut_miit.job_station.dto.user;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class UserUpdateDto {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String login;
    private String password;
    private boolean isCommercialAccount;

    public UserUpdateDto(String id, String firstName, String middleName, String lastName, String login, String password, boolean isCommercialAccount) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.isCommercialAccount = isCommercialAccount;
    }

    @NotBlank(message = "Необходимо передать id пользователя")
    public String getId() {
        return id;
    }

    @NotBlank(message = "Имя не может быть пустым")
    @Length(min = 2, message = "Имя должно содержать как минимум 2 символа")
    public String getFirstName() {
        return firstName;
    }

    @Length(min = 2, message = "Отчество должно содержать как минимум 2 символа")
    public String getMiddleName() {
        return middleName;
    }

    @NotBlank(message = "Фамилия не может быть пустой")
    @Length(min = 2, message = "Фамилия должна содержать как минимум 2 символа")
    public String getLastName() {
        return lastName;
    }

    @NotBlank(message = "Логин не может быть пустым")
    @Length(min = 4, message = "Логин должен содержать как минимум 4 символа")
    public String getLogin() {
        return login;
    }

    @NotBlank(message = "Пароль не может быть пустым")
    @Length(min = 8, message = "Пароль должен содержать как минимум 8 символов")
    public String getPassword() {
        return password;
    }

    public boolean isCommercialAccount() {
        return isCommercialAccount;
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

    public void setCommercialAccount(boolean isCommercialAccount) {
        this.isCommercialAccount = isCommercialAccount;
    }
}
