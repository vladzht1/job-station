package edu.rut_miit.job_station.dto.vacancy;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddVacancyDto {
    private String title;
    private String content;
    private int companyId;
    private float offeredSalary;
    private Set<Integer> skillIds;

    public AddVacancyDto(String title, String content, int companyId, float offeredSalary, Set<Integer> skillIds) {
        this.title = title;
        this.content = content;
        this.companyId = companyId;
        this.offeredSalary = offeredSalary;
        this.skillIds = skillIds;
    }

    @NotNull(message = "Необходимо передать название вакансии")
    @NotBlank(message = "Название вакансии не может быть пустым")
    public String getTitle() {
        return title;
    }

    @NotNull(message = "Необходимо передать описание вакансии")
    @NotBlank(message = "Описание вакансии не может быть пустым")
    public String getContent() {
        return content;
    }

    @NotNull(message = "Необходимо передать id компании")
    public int getCompanyId() {
        return companyId;
    }

    public float getOfferedSalary() {
        return offeredSalary;
    }

    @NotEmpty(message = "Список навыков не может быть пустым")
    public Set<Integer> getSkillIds() {
        return skillIds;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setOfferedSalary(float offeredSalary) {
        this.offeredSalary = offeredSalary;
    }

    public void setSkillIds(Set<Integer> skillIds) {
        this.skillIds = skillIds;
    }
}
