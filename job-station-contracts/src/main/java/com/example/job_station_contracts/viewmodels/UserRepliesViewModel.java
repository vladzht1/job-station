package com.example.job_station_contracts.viewmodels;

import java.util.List;

import com.example.job_station_contracts.models.ReplyViewModel;
import com.example.job_station_contracts.models.VacancyViewModel;

public record UserRepliesViewModel(
  BaseViewModel base,
  List<ReplyViewModel> replies,
  List<VacancyViewModel> userRecommendedVacancies
) {}
