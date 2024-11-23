package edu.rut_miit.job_station.dto.vacancy;

import java.util.Set;

import jakarta.validation.constraints.NotNull;

public class UpdateVacancyDto extends AddVacancyDto {
    private int id;

    public UpdateVacancyDto(int id, String title, String content, int companyId, float offeredSalary, Set<Integer> skillIds) {
        super(title, content, companyId, offeredSalary, skillIds);
        this.id = id;
    }

    @NotNull(message = "Необходимо передать id вакансии")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
