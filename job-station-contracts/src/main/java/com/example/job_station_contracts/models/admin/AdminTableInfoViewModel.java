package com.example.job_station_contracts.models.admin;

public class AdminTableInfoViewModel {
    public String path;
    public String tableTitle;
    public long recordsCount;

    public AdminTableInfoViewModel(String path, String tableTitle, long recordsCount) {
        this.path = path;
        this.tableTitle = tableTitle;
        this.recordsCount = recordsCount;
    }
}
