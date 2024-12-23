package com.example.job_station_contracts.viewmodels;

import java.util.List;

import com.example.job_station_contracts.models.VacancyViewModel;

public record HomeViewModel(
    BaseViewModel base,
    List<VacancyViewModel> vacancies,
    List<VacancyViewModel> userRecommendedVacancies
) {}
