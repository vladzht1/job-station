package edu.rut_miit.job_station.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.dto.skill.SkillRatingDto;
import edu.rut_miit.job_station.entities.Skill;
import edu.rut_miit.job_station.entities.SkillCategory;
import edu.rut_miit.job_station.repositories.SkillRepository;

@Repository
public class SkillRepositoryImpl extends BaseRepository<Skill, String> implements SkillRepository {
    public SkillRepositoryImpl() {
        super(Skill.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SkillRatingDto> findSortedByCompaniesDemands() {
        return entityManager().createNativeQuery("""
                    select skills.id, skills.name, skills.category, count(skills.id) as amount from skills
                    join vacancies_skills on vacancies_skills.skills_id = skills.id
                    join vacancies on vacancies_skills.vacancies_id = vacancies.id
                    group by skills.id
                    order by count(skills.id) desc
                """, SkillRatingDto.class)
            .getResultList();
    }

    @Override
    public List<Skill> findByCategory(SkillCategory category) {
        return entityManager().createQuery("""
                    select s from Skill s
                    where s.category = :category
                """, Skill.class)
            .setParameter("category", category)
            .getResultList();
    }

    @Override
    public Optional<Skill> findByName(String name) {
        List<Skill> skills = entityManager().createQuery("""
                select s from Skill s
                where lower(s.name) like lower(:name)
            """, Skill.class)
            .setParameter("name", name)
            .getResultList();

        if (skills.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(skills.get(0));
    }
}
