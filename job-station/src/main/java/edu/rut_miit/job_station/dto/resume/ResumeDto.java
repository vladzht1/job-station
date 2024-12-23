package edu.rut_miit.job_station.dto.resume;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ResumeDto implements Serializable {
    private String id;
    private String title;
    private String content;
    private String creatorId;
    private String creatorFirstName;
    private String creatorMiddleName;
    private String creatorLastName;
    private int expectedSalary;
    private List<String> skills;
    private LocalDateTime createdAt;
    private boolean isActive;

    public ResumeDto(String id, String title, String content, String creatorId, String creatorFirstName, String creatorMiddleName, String creatorLastName, int expectedSalary, LocalDateTime createdAt, List<String> skills, boolean isActive) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creatorId = creatorId;
        this.creatorFirstName = creatorFirstName;
        this.creatorMiddleName = creatorMiddleName;
        this.creatorLastName = creatorLastName;
        this.expectedSalary = expectedSalary;
        this.createdAt = createdAt;
        this.skills = skills;
        this.isActive = isActive;
    }

    protected ResumeDto() {}

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getCreatorFirstName() {
        return creatorFirstName;
    }

    public String getCreatorMiddleName() {
        return creatorMiddleName;
    }

    public String getCreatorLastName() {
        return creatorLastName;
    }

    public int getExpectedSalary() {
        return expectedSalary;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<String> getSkills() {
        return skills;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public void setCreatorFirstName(String creatorFirstName) {
        this.creatorFirstName = creatorFirstName;
    }

    public void setCreatorMiddleName(String creatorMiddleName) {
        this.creatorMiddleName = creatorMiddleName;
    }

    public void setCreatorLastName(String creatorLastName) {
        this.creatorLastName = creatorLastName;
    }

    public void setExpectedSalary(int expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
