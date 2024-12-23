package edu.rut_miit.job_station.services;

import java.util.List;

import edu.rut_miit.job_station.dto.vacancy.VacancyCreateDto;
import edu.rut_miit.job_station.dto.vacancy.VacancyUpdateDto;
import edu.rut_miit.job_station.dto.vacancy.VacancyDto;

public interface VacancyService {
    List<VacancyDto> findAll();
    List<VacancyDto> findAllVacanciesByCompanyId(String companyId);
    List<VacancyDto> findTop5VacanciesForUser(String userId);
    List<VacancyDto> findRecommendedVacanciesForUser(String userId, int limit);
    List<VacancyDto> findAlikeVacancies(String vacancyId, int limit);
    VacancyDto findById(String id);
    long count();
    VacancyDto addVacancy(VacancyCreateDto vacancyDto);
    VacancyDto updateVacancy(VacancyUpdateDto vacancyDto);
    void activateVacancy(String id);
    void deactivateVacancy(String id);
}
