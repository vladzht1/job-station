package edu.rut_miit.job_station.services;

import edu.rut_miit.job_station.dto.skill.UpdateSkillDto;
import edu.rut_miit.job_station.entities.Skill;
import edu.rut_miit.job_station.entities.SkillCategory;

public interface SkillService {
    Iterable<Skill> findAllSkills();
    Iterable<Skill> findSkillsByCategory(SkillCategory category);
    Skill createSkill(String title);
    Skill createSkill(String title, SkillCategory category);
    Skill updateSkill(UpdateSkillDto skillDto);
}
