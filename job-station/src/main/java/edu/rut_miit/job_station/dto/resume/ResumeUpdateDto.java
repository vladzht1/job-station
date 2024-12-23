package edu.rut_miit.job_station.dto.resume;

import java.util.List;

public class ResumeUpdateDto {
    private String id;
    private String title;
    private String content;
    private int expectedSalary;
    private List<String> skills;

    public ResumeUpdateDto(String id, String title, String content, int expectedSalary, List<String> skills) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.expectedSalary = expectedSalary;
        this.skills = skills;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getExpectedSalary() {
        return expectedSalary;
    }

    public List<String> getSkills() {
        return skills;
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

    public void setExpectedSalary(int expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
