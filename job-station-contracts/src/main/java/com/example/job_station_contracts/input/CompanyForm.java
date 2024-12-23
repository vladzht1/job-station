package com.example.job_station_contracts.input;

import org.hibernate.validator.constraints.Length;

public class CompanyForm {
    private String id;
    private String name;
    private String description;
    private String creatorId;

    public CompanyForm(String id, String name, String description, String creatorId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
    }

    public String getId() {
        return id;
    }

    @Length(min = 3, message = "Название должно содержать минимум 3 символа")
    public String getName() {
        return name;
    }

    @Length(min = 10, message = "Описание должно содержать минимум 10 символов")
    public String getDescription() {
        return description;
    }

    public String getCreatorId() {
        return creatorId;
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

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}
