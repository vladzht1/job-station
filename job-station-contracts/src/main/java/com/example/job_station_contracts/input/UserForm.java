package com.example.job_station_contracts.input;

import org.hibernate.validator.constraints.Length;

public class UserForm {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String login;
    private String password;
    private boolean commercialAccount;

    public UserForm(String id, String firstName, String middleName, String lastName, String login, String password, boolean commercialAccount) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.commercialAccount = commercialAccount;
    }

    public String getId() {
        return id;
    }

    @Length(min = 2, message = "Данное поле должно содержать минимум 2 символа")
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Length(min = 2, message = "Данное поле должно содержать минимум 2 символа")
    public String getLastName() {
        return lastName;
    }

    @Length(min = 5, message = "Данное поле должно содержать минимум 5 символов")
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean getCommercialAccount() {
        return commercialAccount;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setCommercialAccount(boolean commercialAccount) {
        this.commercialAccount = commercialAccount;
    }
}
