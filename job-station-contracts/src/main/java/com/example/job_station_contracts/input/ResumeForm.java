package com.example.job_station_contracts.input;

import java.util.List;

public class ResumeForm {
  private String jobTitle;
  private String salary;
  private String description;
  private List<String> skills;

  public ResumeForm(String jobTitle, String salary, String description, List<String> skills) {
    this.jobTitle = jobTitle;
    this.salary = salary;
    this.description = description;
    this.skills = skills;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public String getSalary() {
    return salary;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getSkills() {
    return skills;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setSkills(List<String> skills) {
    this.skills = skills;
  }
}
