package com.example.job_station_contracts.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/profile")
public interface ProfileController extends BaseController {
    @GetMapping("")
    String profilePage(Model model);
}
