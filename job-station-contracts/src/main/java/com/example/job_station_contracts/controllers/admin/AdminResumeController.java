package com.example.job_station_contracts.controllers.admin;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.BaseController;
import com.example.job_station_contracts.input.ResumeForm;

import jakarta.validation.Valid;

@RequestMapping("/admin/resumes")
public interface AdminResumeController extends BaseController {
    @GetMapping("")
    String resumesPage(Model model);

    @GetMapping("/new")
    String createResumePage(Model model);

    @PostMapping("/new")
    String createResume(@Valid @ModelAttribute("form") ResumeForm resumeForm, BindingResult bindingResult, Model model);

    @GetMapping("/{id}/edit")
    String editResumePage(@PathVariable String id, Model model);

    @PostMapping("/{id}/edit")
    String editResume(@PathVariable String id, @Valid @ModelAttribute("form") ResumeForm resumeForm, BindingResult bindingResult, Model model);
}
