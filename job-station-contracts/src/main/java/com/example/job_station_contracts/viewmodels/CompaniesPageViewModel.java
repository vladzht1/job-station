package com.example.job_station_contracts.viewmodels;

import java.util.List;

import com.example.job_station_contracts.models.CompanyViewModel;

public record CompaniesPageViewModel(
    BaseViewModel base,
    List<CompanyViewModel> companies
) {}
