package edu.rut_miit.job_station.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "skills")
public class Skill extends BaseEntity {
    private String name;
    private SkillCategory category;
    private List<Vacancy> vacancies;
    private List<Resume> resumes;

    public Skill(String name) {
        this(name, SkillCategory.NOT_CATEGORIZED);
    }

    public Skill(String name, SkillCategory category) {
        setName(name);
        setCategory(category);
        setVacancies(new ArrayList<>());
        setResumes(new ArrayList<>());
    }

    protected Skill() {}

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    public SkillCategory getCategory() {
        return category;
    }

    @ManyToMany(mappedBy = "skills")
    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    @ManyToMany(mappedBy = "skills")
    public List<Resume> getResumes() {
        return resumes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(SkillCategory category) {
        this.category = category;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }

    @Override
    public String toString() {
        return name;
    }
}
