package edu.rut_miit.job_station.repositories.impl;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.entities.Company;
import edu.rut_miit.job_station.repositories.CompanyRepository;

@Repository
public class CompanyRepositoryImpl extends BaseRepository<Company, String> implements CompanyRepository {
    public CompanyRepositoryImpl() {
        super(Company.class);
    }
}
