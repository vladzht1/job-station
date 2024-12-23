package com.example.job_station_contracts.controllers.admin;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.BaseController;
import com.example.job_station_contracts.input.CompanyForm;

import jakarta.validation.Valid;

@RequestMapping("/admin/companies")
public interface AdminCompanyController extends BaseController {
    @GetMapping("")
    String companiesPage(Model model);

    @GetMapping("/new")
    String createCompanyPage(Model model);

    @PostMapping("/new")
    String createCompany(@Valid @ModelAttribute("form") CompanyForm companyForm, BindingResult bindingResult, Model model);

    @GetMapping("/{id}/edit")
    String editCompanyPage(@PathVariable String id, Model model);

    @PostMapping("/{id}/edit")
    String editCompany(@PathVariable String id, @Valid @ModelAttribute("form") CompanyForm companyForm, BindingResult bindingResult, Model model);

}
