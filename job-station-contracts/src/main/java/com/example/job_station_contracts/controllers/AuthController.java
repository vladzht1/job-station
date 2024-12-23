package com.example.job_station_contracts.controllers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.job_station_contracts.input.RegisterForm;

import jakarta.validation.Valid;

@RequestMapping("/auth")
public interface AuthController {
    @GetMapping("/login")
    String loginPage(Model model);

    @PostMapping("/login_error")
    String handleLoginError(String username, String password, RedirectAttributes redirectAttributes);

    @GetMapping("/signup")
    String registerPage(Model model);

    @PostMapping("/signup")
    String registerUser(@Valid @ModelAttribute("form") RegisterForm form, BindingResult bindingResult, Model model);
}
