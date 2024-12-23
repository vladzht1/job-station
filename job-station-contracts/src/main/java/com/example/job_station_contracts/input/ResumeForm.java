package com.example.job_station_contracts.input;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ResumeForm {
    private String id;
    private String title;
    private String content;
    private int salary;
    private String skills;
    private String creatorId;

    public ResumeForm(String id, String title, String content, int salary, String skills, String creatorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.salary = salary;
        this.skills = skills;
        this.creatorId = creatorId;
    }

    public String getId() {
        return id;
    }

    @Length(min = 5, message = "Название должности должно содержать минимум 5 символов")
    public String getTitle() {
        return title;
    }

    @Min(value = 0, message = "Данное значение не может быть отрицательным")
    public int getSalary() {
        return salary;
    }

    @Length(min = 15, message = "Информация должна содержать минимум 15 символов")
    public String getContent() {
        return content;
    }

    @NotBlank(message = "Необходимо указать хотя бы один навык")
    public String getSkills() {
        return skills;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}
