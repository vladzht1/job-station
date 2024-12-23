package com.example.job_station_contracts.input;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class VacancyForm {
    private String title;
    private String content;
    private String companyId;
    private int offeredSalary;
    private String skills;

    public VacancyForm(String title, String companyId, int offeredSalary, String content, String skills) {
        this.companyId = companyId;
        this.offeredSalary = offeredSalary;
        this.content = content;
        this.skills = skills;
    }

    @Length(min = 5, message = "Название должности должно содержать минимум 5 символов")
    public String getTitle() {
        return title;
    }

    @Length(min = 15, message = "Информация должна содержать минимум 15 символов")
    public String getContent() {
        return content;
    }

    public String getCompanyId() {
        return companyId;
    }

    @Min(value = 0, message = "Данное значение не может быть отрицательным")
    public int getOfferedSalary() {
        return offeredSalary;
    }

    @NotBlank(message = "Необходимо указать хотя бы один навык")
    public String getSkills() {
        return skills;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setOfferedSalary(int offeredSalary) {
        this.offeredSalary = offeredSalary;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
