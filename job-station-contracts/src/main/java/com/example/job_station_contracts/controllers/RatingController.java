package com.example.job_station_contracts.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rating")
public interface RatingController extends BaseController {
    @GetMapping()
    String ratingPage(Model model);
}
