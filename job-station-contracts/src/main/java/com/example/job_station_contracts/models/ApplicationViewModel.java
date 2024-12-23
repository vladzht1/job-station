package com.example.job_station_contracts.models;

import java.time.LocalDateTime;

public class ApplicationViewModel {
    private String id;
    private ResumeViewModel resume;
    private VacancyViewModel vacancy;
    private LocalDateTime createdAt;
    private String status;

    public ApplicationViewModel(String id, ResumeViewModel resume, VacancyViewModel vacancy, LocalDateTime createdAt, String status) {
        this.id = id;
        this.resume = resume;
        this.vacancy = vacancy;
        this.createdAt = createdAt;
        this.status = status;
    }

    protected ApplicationViewModel() {}

    public String getId() {
        return id;
    }

    public ResumeViewModel getResume() {
        return resume;
    }

    public VacancyViewModel getVacancy() {
        return vacancy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResume(ResumeViewModel resume) {
        this.resume = resume;
    }

    public void setVacancy(VacancyViewModel vacancy) {
        this.vacancy = vacancy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
