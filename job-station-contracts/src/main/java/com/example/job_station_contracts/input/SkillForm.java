package com.example.job_station_contracts.input;

import org.hibernate.validator.constraints.Length;

public class SkillForm {
    private String id;
    private String name;

    public SkillForm(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Length(min = 2, message = "Название должно содержать минимум 2 символа")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
