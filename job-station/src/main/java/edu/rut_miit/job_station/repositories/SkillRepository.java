package edu.rut_miit.job_station.repositories;

import edu.rut_miit.job_station.entities.Skill;
import edu.rut_miit.job_station.repositories.base.CreateRepository;
import edu.rut_miit.job_station.repositories.base.ReadRepository;

public interface SkillRepository extends
    ReadRepository<Skill, String>,
    CreateRepository<Skill, String> {
}
