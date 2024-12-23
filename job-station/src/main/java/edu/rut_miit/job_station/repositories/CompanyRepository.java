package edu.rut_miit.job_station.repositories;

import java.util.List;
import java.util.Optional;

import edu.rut_miit.job_station.dto.company.CompanyRatingDto;
import edu.rut_miit.job_station.entities.Company;
import edu.rut_miit.job_station.repositories.base.CreateRepository;
import edu.rut_miit.job_station.repositories.base.ReadRepository;
import edu.rut_miit.job_station.repositories.base.UpdateRepository;

public interface CompanyRepository extends
    ReadRepository<Company, String>,
    CreateRepository<Company, String>,
    UpdateRepository<Company, String>
{
    List<Company> findByCreatorId(String creatorId);
    List<CompanyRatingDto> findSortedByApplicationsAmountDesc(int limit);
    Optional<Company> findByName(String name);
}
