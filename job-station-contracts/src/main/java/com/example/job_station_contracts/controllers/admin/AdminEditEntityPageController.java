package com.example.job_station_contracts.controllers.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.input.admin.AdminEditEntityInputModel;

@RequestMapping("/admin/entity/{id}/edit")
public interface AdminEditEntityPageController {
  @GetMapping()
  String editEntityPage(@PathVariable String id, Model model);

  @PostMapping
  String editEntity(AdminEditEntityInputModel inputModel);
}
