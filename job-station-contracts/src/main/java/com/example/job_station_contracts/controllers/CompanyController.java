package com.example.job_station_contracts.controllers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.input.CompanyForm;
import com.example.job_station_contracts.input.VacancyForm;

@RequestMapping("/")
public interface CompanyController {
    @GetMapping()
    String companiesPage(Model model);

    @GetMapping("/{id}")
    String companyPage(@PathVariable String id, Model model);

    @GetMapping("/new")
    String createCompanyPage(Model model);

    @PostMapping("/new")
    String createCompany(@ModelAttribute("form") CompanyForm companyForm, BindingResult bindingResult, Model model);

    @GetMapping("/{id}/edit")
    String editCompanyPage(@PathVariable String id, Model model);

    @PostMapping("/{id}/edit")
    String editCompany(@PathVariable String id, CompanyForm companyForm, BindingResult bindingResult, Model model);

    @GetMapping("/{id}/vacancies/new")
    String createVacancyPage(@PathVariable String id, Model model);

    @PostMapping("/{id}/vacancies/new")
    String createVacancy(@PathVariable String id, @ModelAttribute("form") VacancyForm vacancyForm, BindingResult bindingResult, Model model);
}
