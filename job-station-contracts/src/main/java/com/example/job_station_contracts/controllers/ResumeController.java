package com.example.job_station_contracts.controllers;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.input.ResumeForm;

import jakarta.validation.Valid;

@RequestMapping("/resumes")
public interface ResumeController extends BaseController {
    @GetMapping("")
    String userResumesPage(Model model, Principal principal);

    @GetMapping("/{id}")
    String resumePage(String id, Model model, Principal principal);

    @GetMapping("/new")
    String createResumePage(Model model);

    @PostMapping("/new")
    String createResume(@Valid @ModelAttribute("form") ResumeForm resumeForm, BindingResult bindingResult, Model model, Principal principal);

    @GetMapping("/{id}/edit")
    String editResumePage(@PathVariable String id, Model model);

    @PutMapping("/{id}/edit")
    String updateResume(@PathVariable String id, @Valid @ModelAttribute("form") ResumeForm resumeForm, BindingResult bindingResult, Model model, Principal principal);
}
