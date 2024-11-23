package com.example.job_station_contracts.controllers.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
public interface AdminEntityPageController {
  @GetMapping("/entity")
  String entityTablePage(Model model);
}
