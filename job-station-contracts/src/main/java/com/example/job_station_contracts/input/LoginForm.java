package com.example.job_station_contracts.input;

public class LoginForm {
  private String identifier;
  private String password;

  public LoginForm(String identifier, String password) {
    this.identifier = identifier;
    this.password = password;
  }

  public String getIdentifier() {
    return identifier;
  }

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
