package edu.rut_miit.job_station.dto.vacancy;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class VacancyCreateDto {
    private String title;
    private String content;
    private String companyId;
    private int offeredSalary;
    private List<String> skills;

    public VacancyCreateDto(String title, String content, String companyId, int offeredSalary, List<String> skills) {
        this.title = title;
        this.content = content;
        this.companyId = companyId;
        this.offeredSalary = offeredSalary;
        this.skills = skills;
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
    public String getCompanyId() {
        return companyId;
    }

    public int getOfferedSalary() {
        return offeredSalary;
    }

    @NotEmpty(message = "Список навыков не может быть пустым")
    public List<String> getSkills() {
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

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
