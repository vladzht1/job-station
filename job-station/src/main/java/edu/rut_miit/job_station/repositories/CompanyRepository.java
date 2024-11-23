package edu.rut_miit.job_station.repositories;

import edu.rut_miit.job_station.entities.Company;
import edu.rut_miit.job_station.repositories.base.CreateRepository;
import edu.rut_miit.job_station.repositories.base.ReadRepository;
import edu.rut_miit.job_station.repositories.base.UpdateRepository;

public interface CompanyRepository extends
    ReadRepository<Company, String>,
    CreateRepository<Company, String>,
    UpdateRepository<Company, String> {
}
