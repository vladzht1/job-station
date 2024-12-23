package edu.rut_miit.job_station.dto.vacancy;

import java.io.Serializable;
import java.util.List;

import edu.rut_miit.job_station.dto.application.ApplicationDto;
import edu.rut_miit.job_station.dto.company.CompanyDto;

public class VacancyDto implements Serializable {
    private String id;
    private String title;
    private String content;
    private CompanyDto company;
    private String offeredSalary;
    private List<String> skills;
    private List<ApplicationDto> applications;

    public VacancyDto(String id, String title, String content, CompanyDto company, String offeredSalary, List<String> skills, List<ApplicationDto> application) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.company = company;
        this.offeredSalary = offeredSalary;
        this.skills = skills;
        this.applications = application;
    }

    protected VacancyDto() {}

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public String getOfferedSalary() {
        return offeredSalary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<ApplicationDto> getApplications() {
        return applications;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public void setOfferedSalary(String offeredSalary) {
        this.offeredSalary = offeredSalary;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setApplications(List<ApplicationDto> applications) {
        this.applications = applications;
    }
}
