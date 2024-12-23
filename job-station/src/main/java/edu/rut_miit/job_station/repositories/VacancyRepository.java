package edu.rut_miit.job_station.repositories;

import java.util.List;

import edu.rut_miit.job_station.entities.Skill;
import edu.rut_miit.job_station.entities.Vacancy;
import edu.rut_miit.job_station.repositories.base.CreateRepository;
import edu.rut_miit.job_station.repositories.base.ReadRepository;
import edu.rut_miit.job_station.repositories.base.UpdateRepository;

public interface VacancyRepository extends
    ReadRepository<Vacancy, String>,
    CreateRepository<Vacancy, String>,
    UpdateRepository<Vacancy, String>
{
    List<Vacancy> findBySkills(Iterable<Skill> skills);
    List<Vacancy> findAllByCompanyId(String companyId);
    List<Vacancy> findBySkillsCountDesc(Iterable<Skill> skills);
}
