package com.example.job_station_contracts.viewmodels.admin;

import java.util.List;

import com.example.job_station_contracts.viewmodels.BaseViewModel;

public record AdminHomePageViewModel<T>(
    BaseViewModel base,
    List<T> tables
) {}
