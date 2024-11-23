package com.example.job_station_contracts.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.input.admin.AdminCreateEntityInputModel;

@Controller
@RequestMapping("/admin/entity/new")
public interface AdminCreateEntityPageController {
  @GetMapping()
  String createEntityPage(Model model);

  @PostMapping()
  void createEntity(AdminCreateEntityInputModel inputModel);
}
