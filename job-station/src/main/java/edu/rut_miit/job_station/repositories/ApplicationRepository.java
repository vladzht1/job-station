package edu.rut_miit.job_station.repositories;

import java.util.List;

import edu.rut_miit.job_station.entities.Application;
import edu.rut_miit.job_station.repositories.base.CreateRepository;
import edu.rut_miit.job_station.repositories.base.DeleteRepository;
import edu.rut_miit.job_station.repositories.base.ReadRepository;
import edu.rut_miit.job_station.repositories.base.UpdateRepository;

public interface ApplicationRepository extends
    ReadRepository<Application, String>,
    CreateRepository<Application, String>,
    UpdateRepository<Application, String>,
    DeleteRepository<Application, String>
{
    List<Application> findCompanyApplications(String companyId);
    List<Application> findActiveApplicationsToCompany(String companyId);
    List<Application> findUserApplications(String userId);
    List<Application> findVacancyApplications(String vacancyId);
    Application findByVacancyIdAndResumeId(String vacancyId, String resumeId);
}
