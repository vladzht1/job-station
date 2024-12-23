package edu.rut_miit.job_station.dto.company;

import java.io.Serializable;
import java.time.LocalDateTime;

import edu.rut_miit.job_station.dto.user.UserDto;

public class CompanyDto implements Serializable {
    private String id;
    private String name;
    private String description;
    private UserDto creator;
    private LocalDateTime createdAt;

    public CompanyDto(String id, String name, String description, UserDto creator, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creator = creator;
        this.createdAt = createdAt;
    }

    protected CompanyDto() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UserDto getCreator() {
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

    public void setCreator(UserDto creator) {
        this.creator = creator;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
