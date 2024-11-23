package com.example.job_station_contracts.viewmodels;

import java.util.List;

import com.example.job_station_contracts.models.UserViewModel;
import com.example.job_station_contracts.models.VacancyViewModel;

public record ResumeViewModel(
  BaseViewModel base,
  UserViewModel user,
  ResumeViewModel resume,
  List<VacancyViewModel> userRecommendedVacancies
) {}
