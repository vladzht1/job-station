package com.example.job_station_contracts.controllers.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.input.admin.AdminDeleteEntityInputModel;

@RequestMapping("/admin/entity/{id}/delete")
public interface AdminDeleteEntityPageController {
  @GetMapping()
  String confirmDeletePage(@PathVariable String id, Model model);

  @DeleteMapping()
  String deleteEntity(@PathVariable String id, AdminDeleteEntityInputModel inputModel);
}
