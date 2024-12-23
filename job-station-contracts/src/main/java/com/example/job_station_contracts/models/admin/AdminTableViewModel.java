package com.example.job_station_contracts.models.admin;

public class AdminTableViewModel extends AdminTableInfoViewModel {
    public String editUrl;
    public String deleteUrl;

    public AdminTableViewModel(String path, String tableTitle, int recordsCount, String editUrl, String deleteUrl) {
        super(path, tableTitle, recordsCount);
        this.editUrl = editUrl;
        this.deleteUrl = deleteUrl;
    }
}
