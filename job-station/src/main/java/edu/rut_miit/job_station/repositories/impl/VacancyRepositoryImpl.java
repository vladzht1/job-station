package edu.rut_miit.job_station.repositories.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.entities.Skill;
import edu.rut_miit.job_station.entities.Vacancy;
import edu.rut_miit.job_station.repositories.VacancyRepository;

@Repository
public class VacancyRepositoryImpl extends BaseRepository<Vacancy, String> implements VacancyRepository {
    public VacancyRepositoryImpl() {
        super(Vacancy.class);
    }

    @Override
    public List<Vacancy> findBySkills(Iterable<Skill> skills) {
        return entityManager().createQuery("""
                select v from Vacancy v
                join v.skills s
                where s in :skills
            """, Vacancy.class)
            .setParameter("skills", skills)
            .getResultList();
    }

    @Override
    public List<Vacancy> findAllByCompanyId(String companyId) {
        return entityManager().createQuery("""
                select v from Vacancy v
                join v.company c
                where c.id = :company_id
            """, Vacancy.class)
            .setParameter("company_id", companyId)
            .getResultList();
    }

    /**
     * Find and returns vacancies with most intersection with given skills ordered by intersection amount desc
     */
    @Override
    public List<Vacancy> findBySkillsCountDesc(Iterable<Skill> skills) {
        // TODO: Implement
        throw new UnsupportedOperationException("Implement this method");
    }
}
