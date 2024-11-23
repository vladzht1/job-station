package com.example.job_station_contracts.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.input.ResumeForm;

@RequestMapping("/resumes")
public interface ResumeController {
  @GetMapping()
  String userResumesPage(Model model);

  @GetMapping("/{id}")
  String resumePage(String id, Model model);

  @GetMapping("/new")
  String createResumePage(Model model);

  @PostMapping("/new")
  String createResume(ResumeForm resumeForm);

  @GetMapping("/{id}/edit")
  String updateResumePage(@PathVariable String id, Model model);

  @PutMapping("/{id}/edit")
  String updateResume(@PathVariable String id, ResumeForm resumeForm);
}
