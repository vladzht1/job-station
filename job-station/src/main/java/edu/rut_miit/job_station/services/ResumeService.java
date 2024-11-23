package edu.rut_miit.job_station.services;

import edu.rut_miit.job_station.entities.Resume;

public interface ResumeService {
    Iterable<Resume> findAllResumesByUserId(String userId);
    Iterable<Resume> findAllInactiveResumesByUserId(String userId);
}
