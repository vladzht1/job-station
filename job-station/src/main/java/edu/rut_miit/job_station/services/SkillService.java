package edu.rut_miit.job_station.services;

import java.util.List;
import java.util.Set;

import edu.rut_miit.job_station.dto.skill.SkillDto;
import edu.rut_miit.job_station.dto.skill.SkillRatingDto;
import edu.rut_miit.job_station.dto.skill.SkillUpdateDto;
import edu.rut_miit.job_station.entities.Skill;
import edu.rut_miit.job_station.entities.SkillCategory;

public interface SkillService {
    List<SkillDto> findAllSkills();
    List<SkillDto> findSkillsByCategory(SkillCategory category);
    List<SkillRatingDto> findMostPopularSkills();
    Set<Skill> findOrCreateSkillsFrom(List<String> skillNames);
    long count();
    SkillDto createSkill(String title);
    SkillDto createSkill(String title, SkillCategory category);
    SkillDto updateSkill(SkillUpdateDto skillDto);
}
