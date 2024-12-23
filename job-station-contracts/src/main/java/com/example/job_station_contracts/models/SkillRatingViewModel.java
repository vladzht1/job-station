package com.example.job_station_contracts.models;

public class SkillRatingViewModel {
    private String id;
    private String name;
    private String category;
    private long amount;

    public SkillRatingViewModel(String id, String name, String category, long amount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.amount = amount;
    }

    protected SkillRatingViewModel() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(long count) {
        this.amount = count;
    }
}
