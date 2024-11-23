package com.example.job_station_contracts.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.input.VacancyForm;

@RequestMapping("/vacancies")
public interface VacancyController {
  @GetMapping("/{id}")
  String vacancyPage(@PathVariable() String id, Model model);

  @GetMapping("/new")
  String createVacancyPage(Model model);

  @PostMapping("/new")
  String createVacancy(@RequestBody VacancyForm vacancyForm);
}
