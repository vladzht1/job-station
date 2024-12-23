package com.example.job_station_contracts.viewmodels;

import java.util.List;

import com.example.job_station_contracts.models.ResumeViewModel;
import com.example.job_station_contracts.models.UserViewModel;

public record ProfilePageViewModel(
    BaseViewModel base,
    UserViewModel user,
    List<ResumeViewModel> resumes
) {}
