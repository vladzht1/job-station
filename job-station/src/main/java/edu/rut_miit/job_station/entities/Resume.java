package edu.rut_miit.job_station.entities;

import java.time.LocalDateTime;
import java.util.Set;

import edu.rut_miit.job_station.exceptions.ClientException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "resumes")
public class Resume extends BaseEntity {
    private String title;
    private String content;
    private User creator;
    private float expectedSalary;
    private LocalDateTime createdAt;
    private Set<Skill> skills;
    private boolean isActive;

    public Resume(String title, String content, User creator, float expectedSalary, Set<Skill> skills) {
        setTitle(title);
        setContent(content);
        setCreator(creator);
        setExpectedSalary(expectedSalary);
        setSkills(skills);
        setCreationDate(LocalDateTime.now());
        setActive(true);
    }

    protected Resume() {}

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    @ManyToOne
    @JoinColumn(name = "creator_id")
    public User getCreator() {
        return creator;
    }

    @Column(name = "expected_salary")
    public float getExpectedSalary() {
        return expectedSalary;
    }

    @Column
    public LocalDateTime getCreationDate() {
        return createdAt;
    }

    @ManyToMany
    public Set<Skill> getSkills() {
        return skills;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void makeActive() {
        if (!isActive) {
            throw new ClientException.InvalidStateException("This resume is already active");
        }

        this.isActive = true;
    }

    public void makeInactive() {
        if (!isActive) {
            throw new ClientException.InvalidStateException("This resume is already inactive");
        }

        this.isActive = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setExpectedSalary(float expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    protected void setActive(boolean active) {
        this.isActive = active;
    }

    protected void setCreator(User creator) {
        this.creator = creator;
    }

    protected void setCreationDate(LocalDateTime creationDate) {
        this.createdAt = creationDate;
    }
}
