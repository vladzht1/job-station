package com.example.job_station_contracts.input;

import java.util.List;

public class VacancyForm {
  private String companyId;
  private double offeredSalary;
  private String description;
  private List<String> skills;

  public VacancyForm(String companyId, double offeredSalary, String description, List<String> skills) {
    this.companyId = companyId;
    this.offeredSalary = offeredSalary;
    this.description = description;
    this.skills = skills;
  }

  public String getCompanyId() {
    return companyId;
  }

  public double getOfferedSalary() {
    return offeredSalary;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getSkills() {
    return skills;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public void setOfferedSalary(double offeredSalary) {
    this.offeredSalary = offeredSalary;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setSkills(List<String> skills) {
    this.skills = skills;
  }
}
