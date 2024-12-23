package edu.rut_miit.job_station.dto.vacancy;

import java.util.Set;

import jakarta.validation.constraints.NotNull;

public class VacancyUpdateDto {
    private String id;
    private String title;
    private String content;
    private int offeredSalary;
    private Set<String> skills;

    public VacancyUpdateDto(String id, String title, String content, int offeredSalary, Set<String> skills) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.offeredSalary = offeredSalary;
        this.skills = skills;
    }

    @NotNull(message = "Необходимо передать id вакансии")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getOfferedSalary() {
        return offeredSalary;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOfferedSalary(int offeredSalary) {
        this.offeredSalary = offeredSalary;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
