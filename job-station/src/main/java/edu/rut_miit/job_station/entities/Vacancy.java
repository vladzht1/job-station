package edu.rut_miit.job_station.entities;

import edu.rut_miit.job_station.exceptions.ClientException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vacancies")
public class Vacancy extends BaseEntity {
    private String title;
    private String content;
    private Company company;
    private boolean isRecruiting;
    private int offeredSalary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<Skill> skills;
    private List<Application> applications;

    public Vacancy(String title, String content, Company company, int offeredSalary, Set<Skill> skills, List<Application> applications) {
        setTitle(title);
        setContent(content);
        setCompany(company);
        setOfferedSalary(offeredSalary);
        setSkills(skills);
        setApplications(applications);

        setCreationDate(LocalDateTime.now());
        setUpdatedAt(LocalDateTime.now());
    }

    protected Vacancy() {}

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company getCompany() {
        return company;
    }

    @Column(name = "is_recruiting")
    public boolean isRecruiting() {
        return isRecruiting;
    }

    @Column(name = "offered_salary")
    public int getOfferedSalary() {
        return offeredSalary;
    }

    @Column(name = "created_at")
    public LocalDateTime getCreationDate() {
        return createdAt;
    }

    @Column(name = "updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @ManyToMany
    public Set<Skill> getSkills() {
        return skills;
    }

    @OneToMany
    public List<Application> getApplications() {
        return applications;
    }

    public void activate() {
        if (isRecruiting) {
            throw new ClientException.ConflictException("Vacancy is already active");
        }

        setRecruiting(true);
    }

    public void deactivate() {
        if (!isRecruiting) {
            throw new ClientException.ConflictException("Vacancy is already inactive");
        }

        setRecruiting(false);
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
        markAsUpdated();
    }

    public void setTitle(String title) {
        this.title = title;
        markAsUpdated();
    }

    public void setContent(String content) {
        this.content = content;
        markAsUpdated();
    }

    public void setOfferedSalary(int offeredSalary) {
        if (offeredSalary < 0) {
            throw new ClientException.InvalidInputException("Зарплата не может быть отрицательной");
        }

        this.offeredSalary = offeredSalary;
        markAsUpdated();
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
        markAsUpdated();
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    protected void setRecruiting(boolean isRecruiting) {
        this.isRecruiting = isRecruiting;
        markAsUpdated();
    }

    protected void setCompany(Company company) {
        this.company = company;
    }

    protected void setCreationDate(LocalDateTime creationDate) {
        this.createdAt = creationDate;
    }

    protected void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    private void markAsUpdated() {
        this.updatedAt = LocalDateTime.now();
    }
}
