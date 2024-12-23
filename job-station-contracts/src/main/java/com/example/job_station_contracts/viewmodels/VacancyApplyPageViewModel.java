package com.example.job_station_contracts.viewmodels;

import com.example.job_station_contracts.models.VacancyViewModel;

public record VacancyApplyPageViewModel(
    BaseViewModel base,
    VacancyViewModel vacancy
) {}
