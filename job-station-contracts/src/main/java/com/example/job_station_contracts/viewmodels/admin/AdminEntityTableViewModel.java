package com.example.job_station_contracts.viewmodels.admin;

import com.example.job_station_contracts.models.admin.AdminTableViewModel;
import com.example.job_station_contracts.viewmodels.BaseViewModel;

import java.util.List;

public record AdminEntityTableViewModel(
  BaseViewModel base,
  List<AdminTableViewModel> data
) {}
