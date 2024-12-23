package edu.rut_miit.job_station.repositories.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.entities.Application;
import edu.rut_miit.job_station.repositories.ApplicationRepository;

@Repository
public class ApplicationRepositoryImpl extends BaseRepository<Application, String> implements ApplicationRepository {
    public ApplicationRepositoryImpl() {
        super(Application.class);
    }

    @Override
    public List<Application> findCompanyApplications(String companyId) {
        return entityManager().createQuery("""
                select a from Application a
                join a.vacancy v
                join v.company c
                where c.id = :company_id
            """, Application.class)
            .setParameter("company_id", companyId)
            .getResultList();
    }

    @Override
    public List<Application> findActiveApplicationsToCompany(String companyId) {
        return entityManager().createQuery("""
                select a from Application a
                join a.vacancy v
                join v.company c
                where c.id = :company_id and a.status not in ('INVITE', 'REJECT')
            """, Application.class)
            .setParameter("company_id", companyId)
            .getResultList();
    }

    @Override
    public List<Application> findUserApplications(String userId) {
        return entityManager().createQuery("""
                select a from Application a
                join a.resume r
                join r.creator u
                where u.id = :user_id
            """, Application.class)
            .setParameter("user_id", userId)
            .getResultList();
    }

    @Override
    public List<Application> findVacancyApplications(String vacancyId) {
        return entityManager().createQuery("""
            select a from Application a
            join a.vacancy v
            where v.id = :vacancy_id
        """, Application.class)
        .setParameter("vacancy_id", vacancyId)
        .getResultList();
    }

    @Override
    public Application findByVacancyIdAndResumeId(String vacancyId, String resumeId) {
        try {
            return entityManager().createQuery("""
                select a from Application a
                join a.resume r
                join a.vacancy v
                where v.id = :vacancy_id and r.id = :resume_id
            """, Application.class)
            .setParameter("vacancy_id", vacancyId)
            .setParameter("resume_id", resumeId)
            .getSingleResult();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());

            return null;
        }
    }
}
