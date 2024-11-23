package com.example.job_station_contracts.viewmodels;

import java.util.List;

public record VacancyViewModel(
  BaseViewModel base,
  VacancyViewModel vacancy,
  List<VacancyViewModel> alikeVacancies,
  List<VacancyViewModel> userRecommendedVacancies
) {}
