package edu.rut_miit.job_station.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "skills")
public class Skill extends BaseEntity {
    private String name;
    private SkillCategory category;

    public Skill(String name) {
        setName(name);
        setCategory(SkillCategory.NOT_CATEGORIZED);
    }

    public Skill(String name, SkillCategory category) {
        this(name);
        setCategory(category);
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(SkillCategory category) {
        this.category = category;
    }
}
