package edu.rut_miit.job_station.repositories.impl;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.entities.Resume;
import edu.rut_miit.job_station.repositories.ResumeRepository;

@Repository
public class ResumeRepositoryImpl extends BaseRepository<Resume, String> implements ResumeRepository {
    public ResumeRepositoryImpl() {
        super(Resume.class);
    }
}
