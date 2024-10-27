package edu.rut_miit.job_station.repositories;

import edu.rut_miit.job_station.entities.Application;
import edu.rut_miit.job_station.repositories.base.CreateRepository;
import edu.rut_miit.job_station.repositories.base.DeleteRepository;
import edu.rut_miit.job_station.repositories.base.ReadRepository;
import edu.rut_miit.job_station.repositories.base.UpdateRepository;

public interface ApplicationRepository extends
    ReadRepository<Application, String>,
    CreateRepository<Application, String>,
    UpdateRepository<Application, String>,
    DeleteRepository<Application, String> {
}
