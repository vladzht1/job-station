package com.example.job_station_contracts.controllers.admin;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.BaseController;
import com.example.job_station_contracts.input.SkillForm;

import jakarta.validation.Valid;

@RequestMapping("/admin/skills")
public interface AdminSkillController extends BaseController {
    @GetMapping("")
    String skillsPage(Model model);

    @GetMapping("/{id}/edit")
    String editSkillPage(@PathVariable String id, Model model);

    @PostMapping("/{id}/edit")
    String editSkill(@PathVariable String id, @Valid @ModelAttribute("form") SkillForm skillForm, BindingResult bindingResult, Model model);
}
