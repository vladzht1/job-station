package com.example.job_station_contracts.controllers;

import com.example.job_station_contracts.viewmodels.BaseViewModel;

public interface BaseController {
    BaseViewModel createBaseViewModel(String title, String currentUsername);
}
