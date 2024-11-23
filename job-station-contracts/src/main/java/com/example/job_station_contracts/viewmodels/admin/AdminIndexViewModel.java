package com.example.job_station_contracts.viewmodels.admin;

import java.util.List;

import com.example.job_station_contracts.models.admin.AdminTableInfoViewModel;
import com.example.job_station_contracts.viewmodels.BaseViewModel;

public record AdminIndexViewModel(
  BaseViewModel base,
  List<AdminTableInfoViewModel> tables
) {}
