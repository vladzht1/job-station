package edu.rut_miit.job_station.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity {
    private String name;
    private String description;
    private User creator;
    private LocalDateTime createdAt;
    private List<Vacancy> vacancies;

    public Company(String name, String description, User creator) {
        setName(name);
        setDescription(description);
        setCreator(creator);
        setCreatedAt(LocalDateTime.now());
        setVacancies(new ArrayList<>());
    }

    protected Company() {}

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @ManyToOne
    public User getCreator() {
        return creator;
    }

    @Column(name = "created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @OneToMany
    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void addVacancy(Vacancy vacancy) {
        this.vacancies.add(vacancy);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    protected void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
