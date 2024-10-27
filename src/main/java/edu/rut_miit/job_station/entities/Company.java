package edu.rut_miit.job_station.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity {
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Set<Vacancy> vacancies;

    public Company(String name, String description) {
        setName(name);
        setDescription(description);
        setCreationDate(LocalDateTime.now());
        setVacancies(new HashSet<>());
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

    @Column(name = "created_at")
    public LocalDateTime getCreationDate() {
        return createdAt;
    }

    @OneToMany
    public Set<Vacancy> getVacancies() {
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

    public void setVacancies(Set<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    protected void setCreationDate(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
