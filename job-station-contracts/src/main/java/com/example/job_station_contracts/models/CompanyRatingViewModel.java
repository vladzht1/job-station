package com.example.job_station_contracts.models;

public class CompanyRatingViewModel {
    private String id;
    private String name;
    private String description;
    private long amount;

    public CompanyRatingViewModel(String id, String name, String description, long amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    protected CompanyRatingViewModel() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getAmount() {
        return amount;
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

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
