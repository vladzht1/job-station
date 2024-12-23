package com.example.job_station_contracts.input;

public class ApplicationForm {
    private String resumeId;
    private String vacancyId;

    public ApplicationForm(String resumeId, String vacancyId) {
        this.resumeId = resumeId;
        this.vacancyId = vacancyId;
    }

    protected ApplicationForm() {}

    public String getResumeId() {
        return resumeId;
    }

    public String getVacancyId() {
        return vacancyId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public void setVacancyId(String vacancyId) {
        this.vacancyId = vacancyId;
    }
}
