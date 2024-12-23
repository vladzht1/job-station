package edu.rut_miit.job_station.dto.resume;

import java.util.List;
import java.util.Map;

public class ResumeCreateDto {
    private String title;
    private String content;
    private String creatorId;
    private int expectedSalary;
    private List<String> skills;
    private Map<String, String> contacts;

    public ResumeCreateDto(String title, String content, String creatorId, int expectedSalary, List<String> skills, Map<String, String> contacts) {
        this.title = title;
        this.content = content;
        this.creatorId = creatorId;
        this.expectedSalary = expectedSalary;
        this.skills = skills;
        this.contacts = contacts;
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

    public int getExpectedSalary() {
        return expectedSalary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public Map<String, String> getContacts() {
        return contacts;
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

    public void setExpectedSalary(int expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setContacts(Map<String, String> contacts) {
        this.contacts = contacts;
    }
}
