package edu.rut_miit.job_station.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.rut_miit.job_station.dto.application.ApplicationDto;
import edu.rut_miit.job_station.entities.Application;
import edu.rut_miit.job_station.entities.ApplicationStatus;
import edu.rut_miit.job_station.entities.Resume;
import edu.rut_miit.job_station.entities.Vacancy;
import edu.rut_miit.job_station.exceptions.ClientException;
import edu.rut_miit.job_station.repositories.ApplicationRepository;
import edu.rut_miit.job_station.repositories.ResumeRepository;
import edu.rut_miit.job_station.repositories.VacancyRepository;
import edu.rut_miit.job_station.services.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private ApplicationRepository applicationRepository;
    private ResumeRepository resumeRepository;
    private VacancyRepository vacancyRepository;

    private ModelMapper modelMapper;

    @Override
    public List<ApplicationDto> findAllApplicationsToCompany(String companyId) {
        return applicationRepository
            .findCompanyApplications(companyId)
            .stream()
            .map(a -> modelMapper.map(a, ApplicationDto.class))
            .toList();
    }

    @Override
    public List<ApplicationDto> findActiveApplicationsToCompany(String companyId) {
        return applicationRepository
            .findActiveApplicationsToCompany(companyId)
            .stream()
            .map(a -> modelMapper.map(a, ApplicationDto.class))
            .toList();
    }

    @Override
    public List<ApplicationDto> findAllUserApplications(String userId) {
        return applicationRepository
            .findUserApplications(userId)
            .stream()
            .map(a -> modelMapper.map(a, ApplicationDto.class))
            .toList();
    }

    @Override
    public ApplicationDto applyToVacancy(String vacancyId, String resumeId) {
        // All applications to current vacancy
        List<Application> applications = applicationRepository.findVacancyApplications(vacancyId);

        for (var application : applications) {
            // This resume was already applied to this vacancy
            if (application.getResume().getId().equals(resumeId)) {
                return modelMapper.map(application, ApplicationDto.class);
            }
        }

        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow(() -> new ClientException.NotFoundException("Vacancy was not found"));
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ClientException.NotFoundException("Resume was not found"));

        Application application = new Application(resume, vacancy);

        return modelMapper.map(applicationRepository.save(application), ApplicationDto.class);
    }

    @Override
    public boolean checkUserAppliedToVacancy(String vacancyId, String resumeId) {
        return applicationRepository.findByVacancyIdAndResumeId(vacancyId, resumeId) != null;
    }

    @Override
    public void markAsRead(String applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new ClientException.NotFoundException("Application was not found"));

        // Status is already greater than the given one
        if (application.getStatus().value() >= ApplicationStatus.SEEN.value()) {
            return;
        }

        application.markAsSeen();
        applicationRepository.update(application);
    }

    @Override
    public void accept(String applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new ClientException.NotFoundException("Application was not found"));

        // Status is already greater than the given one
        if (application.getStatus().value() >= ApplicationStatus.INVITE.value()) {
            return;
        }

        application.invite();
        applicationRepository.update(application);
    }

    @Override
    public void deny(String applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new ClientException.NotFoundException("Application was not found"));

        // Status is already greater than the given one
        if (application.getStatus().value() >= ApplicationStatus.REJECT.value()) {
            return;
        }

        application.reject();
        applicationRepository.update(application);
    }

    @Autowired
    public void setApplicationRepository(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Autowired
    public void setResumeRepository(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Autowired
    public void setVacancyRepository(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
