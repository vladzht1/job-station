package com.example.job_station_contracts.viewmodels;

import java.util.List;

import com.example.job_station_contracts.models.CompanyRatingViewModel;
import com.example.job_station_contracts.models.SkillRatingViewModel;

public record RatingPageViewModel(
    BaseViewModel base,
    List<CompanyRatingViewModel> companies,
    List<SkillRatingViewModel> skills
) {}
