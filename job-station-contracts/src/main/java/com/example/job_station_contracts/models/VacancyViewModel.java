package com.example.job_station_contracts.models;

import java.util.List;

public class VacancyViewModel {
    private String id;
    private String title;
    private String description;
    private CompanyViewModel company;
    private String salary;
    private List<String> skills;
    private boolean userAppliedToVacancy;

    public VacancyViewModel(String id, String title, String description, CompanyViewModel company, String salary, List<String> skills, boolean userAppliedToVacancy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.company = company;
        this.salary = salary;
        this.skills = skills;
        this.userAppliedToVacancy = userAppliedToVacancy;
    }

    protected VacancyViewModel() {}

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public CompanyViewModel getCompany() {
        return company;
    }

    public String getSalary() {
        return salary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public boolean isUserAppliedToVacancy() {
        return userAppliedToVacancy;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompany(CompanyViewModel company) {
        this.company = company;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setUserAppliedToVacancy(boolean userAppliedToVacancy) {
        this.userAppliedToVacancy = userAppliedToVacancy;
    }
}
