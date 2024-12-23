package com.example.job_station_contracts.models;

import java.time.LocalDateTime;
import java.util.List;

public class ResumeViewModel {
    private String id;
    private String userId;
    private String userFirstName;
    private String userMiddleName;
    private String userLastName;
    private String title;
    private String content;
    private String photoUrl;
    private String salary;
    private List<String> skills;
    private LocalDateTime createdAt;

    public ResumeViewModel(String id, String userId, String userFirstName, String userMiddleName, String userLastName, String title, String content, String photoUrl, String salary, List<String> skills, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userMiddleName = userLastName;
        this.userLastName = userLastName;
        this.title = title;
        this.content = content;
        this.photoUrl = photoUrl;
        this.salary = salary;
        this.skills = skills;
        this.createdAt = createdAt;
    }

    protected ResumeViewModel() {}

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getSalary() {
        return salary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
