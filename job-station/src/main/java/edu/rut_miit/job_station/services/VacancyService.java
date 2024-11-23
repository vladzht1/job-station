package edu.rut_miit.job_station.services;

import edu.rut_miit.job_station.dto.vacancy.AddVacancyDto;
import edu.rut_miit.job_station.dto.vacancy.UpdateVacancyDto;
import edu.rut_miit.job_station.entities.Vacancy;

public interface VacancyService {
    Iterable<Vacancy> findAllVacanciesByCompanyId(String companyId);
    Iterable<Vacancy> findRecommendedVacanciesForUser(String userId);
    Vacancy addVacancy(AddVacancyDto vacancyDto);
    Vacancy updateVacancy(UpdateVacancyDto vacancyDto);
    void activateVacancy(String id);
    void deactivateVacancy(String id);
}
