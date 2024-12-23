package com.example.job_station_contracts.controllers.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.BaseController;

@RequestMapping("/admin")
public interface AdminHomeController extends BaseController {
  @GetMapping()
  String indexPage(Model model);
}
