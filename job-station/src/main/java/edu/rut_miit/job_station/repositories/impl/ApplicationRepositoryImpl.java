package edu.rut_miit.job_station.repositories.impl;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.entities.Application;
import edu.rut_miit.job_station.repositories.ApplicationRepository;

@Repository
public class ApplicationRepositoryImpl extends BaseRepository<Application, String> implements ApplicationRepository {
    public ApplicationRepositoryImpl() {
        super(Application.class);
    }
}
