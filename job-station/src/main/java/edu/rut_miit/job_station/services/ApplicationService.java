package edu.rut_miit.job_station.services;

import java.util.List;

import edu.rut_miit.job_station.dto.application.ApplicationDto;

public interface ApplicationService {
    List<ApplicationDto> findAllApplicationsToCompany(String companyId);
    List<ApplicationDto> findActiveApplicationsToCompany(String companyId);
    List<ApplicationDto> findAllUserApplications(String userId);
    ApplicationDto applyToVacancy(String vacancyId, String resumeId);
    boolean checkUserAppliedToVacancy(String vacancyId, String userId);
    void markAsRead(String applicationId);
    void accept(String applicationId);
    void deny(String applicationId);
}
