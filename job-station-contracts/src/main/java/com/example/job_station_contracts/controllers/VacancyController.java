package com.example.job_station_contracts.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.input.ApplicationForm;

@RequestMapping("/vacancies")
public interface VacancyController extends BaseController {
    @GetMapping("/{id}")
    String vacancyPage(@PathVariable() String id, Model model);

    @GetMapping("/{id}/apply")
    String applyForVacancyPage(@PathVariable() String id, Model model);

    @PostMapping("/{vacancyId}/apply")
    String applyForVacancy(@PathVariable String vacancyId, @ModelAttribute("form") ApplicationForm applicationForm, Model model);
}
