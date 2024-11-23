package edu.rut_miit.job_station.repositories;

import edu.rut_miit.job_station.entities.Resume;
import edu.rut_miit.job_station.repositories.base.CreateRepository;
import edu.rut_miit.job_station.repositories.base.ReadRepository;
import edu.rut_miit.job_station.repositories.base.UpdateRepository;

public interface ResumeRepository extends
    ReadRepository<Resume, String>,
    CreateRepository<Resume, String>,
    UpdateRepository<Resume, String> {
}
