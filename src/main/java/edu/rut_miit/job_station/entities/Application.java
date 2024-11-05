package edu.rut_miit.job_station.entities;

import edu.rut_miit.job_station.exceptions.ClientException;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "applications")
public class Application extends BaseEntity {
    private Resume resume;
    private Vacancy vacancy;
    private LocalDateTime createdAt;
    private ApplicationStatus status;

    public Application(Resume resume, Vacancy vacancy) {
        setResume(resume);
        setVacancy(vacancy);
        setCreationDate(LocalDateTime.now());
        setStatus(ApplicationStatus.UNSEEN);
    }

    protected Application() {}

    @ManyToOne
    @JoinColumn(name = "resume_id")
    public Resume getResume() {
        return resume;
    }

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    public Vacancy getVacancy() {
        return vacancy;
    }

    @Column(name = "created_at")
    public LocalDateTime getCreationDate() {
        return createdAt;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public ApplicationStatus getStatus() {
        return status;
    }

    public void markAsSeen() {
        this.status = ApplicationStatus.SEEN;
    }

    public void invite() {
        this.status = ApplicationStatus.INVITE;
    }

    public void reject() {
        this.status = ApplicationStatus.REJECT;
    }

    protected void setResume(Resume resume) {
        if (resume == null) {
            throw new ClientException.InvalidInputException("Резюме не может быть пустым");
        }

        this.resume = resume;
    }

    protected void setVacancy(Vacancy vacancy) {
        if (vacancy == null) {
            throw new ClientException.InvalidInputException("Вакансия не может быть пустой");
        }

        this.vacancy = vacancy;
    }

    protected void setCreationDate(LocalDateTime creationDate) {
        this.createdAt = creationDate;
    }

    protected void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
