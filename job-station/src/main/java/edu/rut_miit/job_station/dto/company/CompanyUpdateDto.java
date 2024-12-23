package edu.rut_miit.job_station.dto.company;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class CompanyUpdateDto {
    private String name;
    private String description;

    public CompanyUpdateDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @NotBlank(message = "Название компании не может быть пустым")
    @Length(min = 3, message = "Название компании должно содержать более 2 символов")
    public String getName() {
        return name;
    }

    @NotBlank(message = "Описание компании не может быть пустым")
    @Length(min = 15, message = "Описание компании должно содержать более 14 символов")
    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
