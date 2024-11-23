package edu.rut_miit.job_station.services;

import java.util.Optional;

import edu.rut_miit.job_station.dto.company.AddCompanyDto;
import edu.rut_miit.job_station.entities.Company;

public interface CompanyService {
    Iterable<Company> findAllCompanies();
    Optional<Company> findCompanyById(String id);
    Company registerCompany(AddCompanyDto companyDto);
}
