package edu.rut_miit.job_station.dto.skill;

import java.io.Serializable;

public class SkillDto implements Serializable {
    private String id;
    private String name;
    private String category;
    private int usedInVacancies;
    private int usedInResumes;

    public SkillDto(String id, String name, String category, int usedInVacancies, int usedInResumes) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.usedInVacancies = usedInVacancies;
        this.usedInResumes = usedInResumes;
    }

    protected SkillDto() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getUsedInVacancies() {
        return usedInVacancies;
    }

    public void setUsedInVacancies(int usedInVacancies) {
        this.usedInVacancies = usedInVacancies;
    }

    public int getUsedInResumes() {
        return usedInResumes;
    }

    public void setUsedInResumes(int usedInResumes) {
        this.usedInResumes = usedInResumes;
    }
}
