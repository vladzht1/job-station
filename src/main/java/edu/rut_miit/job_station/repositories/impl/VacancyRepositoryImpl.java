package edu.rut_miit.job_station.repositories.impl;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.entities.Vacancy;
import edu.rut_miit.job_station.repositories.VacancyRepository;

@Repository
public class VacancyRepositoryImpl extends BaseRepository<Vacancy, String> implements VacancyRepository {
    public VacancyRepositoryImpl() {
        super(Vacancy.class);
    }
}
