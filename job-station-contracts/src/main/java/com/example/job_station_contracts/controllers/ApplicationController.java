package com.example.job_station_contracts.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/applications")
public interface ApplicationController extends BaseController {
    @GetMapping()
    String userApplicationsPage(Model model);

    @PatchMapping("/{id}/accept")
    String acceptReply(String id, HttpServletRequest request);

    @PatchMapping("/{id}/deny")
    String denyReply(String id, HttpServletRequest request);
}
