package com.example.job_station_contracts.viewmodels;

import java.util.List;

import com.example.job_station_contracts.models.ApplicationViewModel;

public record UserApplicationsPageViewModel(
    BaseViewModel base,
    List<ApplicationViewModel> applications
) {}
