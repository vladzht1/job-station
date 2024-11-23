package edu.rut_miit.job_station.repositories;

import edu.rut_miit.job_station.entities.Vacancy;
import edu.rut_miit.job_station.repositories.base.CreateRepository;
import edu.rut_miit.job_station.repositories.base.ReadRepository;
import edu.rut_miit.job_station.repositories.base.UpdateRepository;

public interface VacancyRepository extends
    ReadRepository<Vacancy, String>,
    CreateRepository<Vacancy, String>,
    UpdateRepository<Vacancy, String> {
}
