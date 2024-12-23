package com.example.job_station_contracts.viewmodels.admin;

import com.example.job_station_contracts.viewmodels.BaseViewModel;

public record AdminConfirmEntityDeleteViewModel(
    BaseViewModel base,
    String entityId,
    String entityName
) {}
