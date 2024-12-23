package edu.rut_miit.job_station.services;

import java.util.List;

import edu.rut_miit.job_station.dto.company.CompanyCreateDto;
import edu.rut_miit.job_station.dto.company.CompanyDto;
import edu.rut_miit.job_station.dto.company.CompanyRatingDto;
import edu.rut_miit.job_station.dto.company.CompanyUpdateDto;

public interface CompanyService {
    List<CompanyDto> findAllCompanies();
    List<CompanyDto> findCompaniesByCreatorId(String creatorId);
    List<CompanyRatingDto> findMostRankedCompanies();
    CompanyDto findCompanyById(String id);
    long count();
    CompanyDto registerCompany(CompanyCreateDto companyDto, String creatorId);
    CompanyDto updateCompany(String id, CompanyUpdateDto companyDto);
}
