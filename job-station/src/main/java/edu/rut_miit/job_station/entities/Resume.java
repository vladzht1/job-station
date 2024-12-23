package edu.rut_miit.job_station.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import edu.rut_miit.job_station.exceptions.ClientException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "resumes")
public class Resume extends BaseEntity {
    private String title;
    private String content;
    private User creator;
    private int expectedSalary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<Skill> skills;
    private List<Contact> contacts;
    private boolean active;

    public Resume(String title, String content, User creator, int expectedSalary, Set<Skill> skills, List<Contact> contacts) {
        setTitle(title);
        setContent(content);
        setCreator(creator);
        setExpectedSalary(expectedSalary);
        setSkills(skills);
        setCreatedAt(LocalDateTime.now());
        setUpdatedAt(LocalDateTime.now());
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
    public int getExpectedSalary() {
        return expectedSalary;
    }

    @Column
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Column
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @ManyToMany
    public Set<Skill> getSkills() {
        return skills;
    }

    @OneToMany
    public List<Contact> getContacts() {
        return contacts;
    }

    @Column()
    public boolean isActive() {
        return active;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void makeActive() {
        if (!active) {
            throw new ClientException.InvalidStateException("This resume is already active");
        }

        this.active = true;
    }

    public void makeInactive() {
        if (!active) {
            throw new ClientException.InvalidStateException("This resume is already inactive");
        }

        this.active = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setExpectedSalary(int expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    protected void setActive(boolean active) {
        this.active = active;
    }

    protected void setCreator(User creator) {
        this.creator = creator;
    }

    protected void setCreatedAt(LocalDateTime creationDate) {
        this.createdAt = creationDate;
    }

    protected void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
