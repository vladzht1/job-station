package com.example.job_station_contracts.input.admin;

public class AdminDeleteEntityInputModel {
  private String id;

  public AdminDeleteEntityInputModel(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
