package com.example.job_station_contracts.viewmodels;

import java.util.List;

import com.example.job_station_contracts.models.ResumeViewModel;
import com.example.job_station_contracts.models.VacancyViewModel;

public record UserResumesPageViewModel(
    BaseViewModel base,
    List<ResumeViewModel> resumes,
    List<VacancyViewModel> userRecommendedVacancies
) {}
