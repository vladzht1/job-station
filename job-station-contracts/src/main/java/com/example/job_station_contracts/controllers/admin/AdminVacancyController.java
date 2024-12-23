package com.example.job_station_contracts.controllers.admin;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.BaseController;
import com.example.job_station_contracts.input.VacancyForm;

import jakarta.validation.Valid;

@RequestMapping("/admin/vacancies")
public interface AdminVacancyController extends BaseController {
    @GetMapping("")
    String usersPage(Model model);

    @GetMapping("/{id}/edit")
    String editVacancyPage(@PathVariable String id, Model model);

    @PostMapping("/{id}/edit")
    String editVacancy(@PathVariable String id, @Valid @ModelAttribute("form") VacancyForm vacancyForm, BindingResult bindingResult, Model model);
}
