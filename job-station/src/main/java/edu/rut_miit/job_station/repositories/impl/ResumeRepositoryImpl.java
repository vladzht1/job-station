package edu.rut_miit.job_station.repositories.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.entities.Resume;
import edu.rut_miit.job_station.repositories.ResumeRepository;

@Repository
public class ResumeRepositoryImpl extends BaseRepository<Resume, String> implements ResumeRepository {
    public ResumeRepositoryImpl() {
        super(Resume.class);
    }

    @Override
    public List<Resume> findAllByUserId(String userId) {
        return entityManager().createQuery("""
                select r from Resume r
                join r.creator c
                where c.id = :user_id
            """, Resume.class)
            .setParameter("user_id", userId)
            .getResultList();
    }

    @Override
    public List<Resume> findAllByUserIdAndActive(String userId, boolean active) {
        return entityManager().createQuery("""
                select r from Resume r
                join r.creator c
                where c.id = :user_id and r.active = :active
            """, Resume.class)
            .setParameter("user_id", userId)
            .setParameter("active", active)
            .getResultList();
    }
}
