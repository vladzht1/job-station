package com.example.job_station_contracts.controllers.admin;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.BaseController;
import com.example.job_station_contracts.input.UserForm;

import jakarta.validation.Valid;

@RequestMapping("/admin/users")
public interface AdminUserController extends BaseController {
    @GetMapping("")
    String usersPage(Model model);

    @GetMapping("/new")
    String createUserPage(Model model);

    @PostMapping("/new")
    String createUser(@Valid @ModelAttribute("form") UserForm userForm, BindingResult bindingResult, Model model);

    @GetMapping("/{id}/edit")
    String editUserPage(@PathVariable String id, Model model);

    @PostMapping("/{id}/edit")
    String editUser(@PathVariable String id, @Valid @ModelAttribute("form") UserForm userForm, BindingResult bindingResult, Model model);

    @GetMapping("/{id}/block")
    String blockUser(@PathVariable String id, Model model);

    @GetMapping("/{id}/unblock")
    String unblockUser(@PathVariable String id, Model model);
}
