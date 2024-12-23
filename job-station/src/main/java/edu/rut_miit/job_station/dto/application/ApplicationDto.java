package edu.rut_miit.job_station.dto.application;

import java.io.Serializable;
import java.time.LocalDateTime;

import edu.rut_miit.job_station.dto.resume.ResumeDto;
import edu.rut_miit.job_station.dto.vacancy.VacancyDto;
import edu.rut_miit.job_station.entities.ApplicationStatus;

public class ApplicationDto implements Serializable {
    private String id;
    private ResumeDto resume;
    private VacancyDto vacancy;
    private LocalDateTime createdAt;
    private ApplicationStatus status;

    public ApplicationDto(String id, ResumeDto resume, VacancyDto vacancy, LocalDateTime createdAt, ApplicationStatus status) {
        this.id = id;
        this.resume = resume;
        this.vacancy = vacancy;
        this.createdAt = createdAt;
        this.status = status;
    }

    protected ApplicationDto() {}

    public String getId() {
        return id;
    }

    public ResumeDto getResume() {
        return resume;
    }

    public VacancyDto getVacancy() {
        return vacancy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResume(ResumeDto resume) {
        this.resume = resume;
    }

    public void setVacancy(VacancyDto vacancy) {
        this.vacancy = vacancy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
