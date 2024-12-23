package edu.rut_miit.job_station.repositories;

import java.util.List;
import java.util.Optional;

import edu.rut_miit.job_station.dto.skill.SkillRatingDto;
import edu.rut_miit.job_station.entities.Skill;
import edu.rut_miit.job_station.entities.SkillCategory;
import edu.rut_miit.job_station.repositories.base.CreateRepository;
import edu.rut_miit.job_station.repositories.base.ReadRepository;

public interface SkillRepository extends
    ReadRepository<Skill, String>,
    CreateRepository<Skill, String>
{
    List<Skill> findByCategory(SkillCategory category);
    List<SkillRatingDto> findSortedByCompaniesDemands();
    Optional<Skill> findByName(String name);
}
