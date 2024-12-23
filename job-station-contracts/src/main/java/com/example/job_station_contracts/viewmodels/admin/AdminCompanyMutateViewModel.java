package com.example.job_station_contracts.viewmodels.admin;

import java.util.List;

import com.example.job_station_contracts.models.UserViewModel;
import com.example.job_station_contracts.viewmodels.BaseViewModel;

public record AdminCompanyMutateViewModel(
    BaseViewModel base,
    List<UserViewModel> users
) {}
