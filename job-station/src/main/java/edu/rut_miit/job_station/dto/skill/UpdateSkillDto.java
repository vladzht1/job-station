package edu.rut_miit.job_station.dto.skill;

import org.hibernate.validator.constraints.Length;

import edu.rut_miit.job_station.entities.SkillCategory;

public class UpdateSkillDto {
    private String id;
    private String name;
    private SkillCategory category;

    public UpdateSkillDto(String id, String name, SkillCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    @Length(min = 2, message = "Название навыка должно содержать более 1 символа")
    public String getName() {
        return name;
    }

    public SkillCategory getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(SkillCategory category) {
        this.category = category;
    }
}
