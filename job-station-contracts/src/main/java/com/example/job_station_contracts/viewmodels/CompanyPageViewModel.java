package com.example.job_station_contracts.viewmodels;

import java.util.List;

import com.example.job_station_contracts.models.CompanyViewModel;
import com.example.job_station_contracts.models.VacancyViewModel;

public record CompanyPageViewModel(
    BaseViewModel base,
    CompanyViewModel company,
    List<VacancyViewModel> vacancies
) {}
