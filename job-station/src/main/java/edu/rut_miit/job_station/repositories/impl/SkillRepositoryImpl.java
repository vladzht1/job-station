package edu.rut_miit.job_station.repositories.impl;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.entities.Skill;
import edu.rut_miit.job_station.repositories.SkillRepository;

@Repository
public class SkillRepositoryImpl extends BaseRepository<Skill, String> implements SkillRepository {
    public SkillRepositoryImpl() {
        super(Skill.class);
    }
}
