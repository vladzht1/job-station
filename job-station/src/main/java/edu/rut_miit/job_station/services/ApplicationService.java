package edu.rut_miit.job_station.services;

import edu.rut_miit.job_station.entities.Application;

public interface ApplicationService {
    Iterable<Application> findAllApplicationsToCompany(String companyId);
    Iterable<Application> findAllUserApplications(String userId);
    Application applyToVacancy(String vacancyId, String resumeId);
    void markAsRead(String applicationId);
    void accept(String applicationId);
    void deny(String applicationId);
}
