package com.example.job_station_contracts.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.input.RegisterForm;

@RequestMapping("/register")
public interface RegisterController {
  @GetMapping()
  String registerPage(Model model);

  @PostMapping()
  String register(RegisterForm registerForm);
}
