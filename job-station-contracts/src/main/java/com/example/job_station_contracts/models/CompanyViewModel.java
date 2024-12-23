package com.example.job_station_contracts.models;

import java.time.LocalDateTime;

public class CompanyViewModel {
    private String id;
    private String name;
    private String description;
    private UserViewModel creator;
    private LocalDateTime createdAt;

    public CompanyViewModel(String id, String name, String description, UserViewModel creator, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creator = creator;
        this.createdAt = createdAt;
    }

    protected CompanyViewModel() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UserViewModel getCreator() {
        return creator;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreator(UserViewModel creator) {
        this.creator = creator;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
