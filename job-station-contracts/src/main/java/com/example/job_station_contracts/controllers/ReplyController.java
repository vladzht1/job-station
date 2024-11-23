package com.example.job_station_contracts.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/replies")
public interface ReplyController {
  @GetMapping()
  String userRepliesPage(Model model);

  @PatchMapping("/{id}/accept")
  String acceptReply(String id);

  @PatchMapping("/{id}/deny")
  String denyReply(String id);
}
