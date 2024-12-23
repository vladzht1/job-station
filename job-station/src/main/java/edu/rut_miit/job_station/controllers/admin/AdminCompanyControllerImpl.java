package edu.rut_miit.job_station.controllers.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.admin.AdminCompanyController;
import com.example.job_station_contracts.input.CompanyForm;
import com.example.job_station_contracts.models.CompanyViewModel;
import com.example.job_station_contracts.models.UserViewModel;
import com.example.job_station_contracts.viewmodels.admin.AdminCompanyMutateViewModel;
import com.example.job_station_contracts.viewmodels.admin.AdminEntityTableViewModel;

import edu.rut_miit.job_station.controllers.BaseControllerImpl;
import edu.rut_miit.job_station.dto.company.CompanyCreateDto;
import edu.rut_miit.job_station.dto.company.CompanyDto;
import edu.rut_miit.job_station.dto.company.CompanyUpdateDto;
import edu.rut_miit.job_station.services.CompanyService;
import edu.rut_miit.job_station.services.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/companies")
public class AdminCompanyControllerImpl extends BaseControllerImpl implements AdminCompanyController {
    private CompanyService companyService;
    private UserService userService;

    public AdminCompanyControllerImpl(CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @Override
    @GetMapping("")
    public String companiesPage(Model model) {
        List<CompanyViewModel> companiesViewModel = companyService.findAllCompanies().stream().map(c -> modelMapper().map(c, CompanyViewModel.class)).toList();

        setBasicAttributes(new AdminEntityTableViewModel<>(
            createBaseViewModel("Админ панель - компании"),
            companiesViewModel
        ), getLoggedUser(), model);

        return "pages/admin/companies-page";
    }

    @Override
    @GetMapping("/new")
    public String createCompanyPage(Model model) {
        List<UserViewModel> usersViewModel = userService.findAllUsers().stream().map(u -> modelMapper().map(u, UserViewModel.class)).toList();

        setBasicAttributes(new AdminCompanyMutateViewModel(
            createBaseViewModel("Админ панель - создание компании"),
            usersViewModel
        ), getLoggedUser(), model);
        model.addAttribute("form", new CompanyForm(null, "", "", ""));

        return "pages/admin/company-create-page";
    }

    @Override
    @PostMapping("/new")
    public String createCompany(@Valid CompanyForm companyForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            List<UserViewModel> usersViewModel = userService.findAllUsers().stream().map(u -> modelMapper().map(u, UserViewModel.class)).toList();

            setBasicAttributes(new AdminCompanyMutateViewModel(
                createBaseViewModel("Админ панель - создание компании"),
                usersViewModel
            ), getLoggedUser(), model);
            model.addAttribute("form", companyForm);

            return "pages/admin/company-create-page";
        }

        var companyDto = new CompanyCreateDto(companyForm.getName(), companyForm.getDescription());

        companyService.registerCompany(companyDto, companyForm.getCreatorId());
        return "redirect:/admin/companies";
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editCompanyPage(@PathVariable String id, Model model) {
        CompanyDto companyDto = companyService.findCompanyById(id);

        List<UserViewModel> usersViewModel = userService.findAllUsers().stream().map(u -> modelMapper().map(u, UserViewModel.class)).toList();

        setBasicAttributes(new AdminCompanyMutateViewModel(
            createBaseViewModel("Админ панель - изменение данных компании"),
            usersViewModel
        ), getLoggedUser(), model);

        model.addAttribute("form", new CompanyForm(
            companyDto.getId(),
            companyDto.getName(),
            companyDto.getDescription(),
            companyDto.getCreator().getId()
        ));

        return "pages/admin/company-edit-page";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String editCompany(String id, @Valid CompanyForm companyForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            List<UserViewModel> usersViewModel = userService.findAllUsers().stream().map(u -> modelMapper().map(u, UserViewModel.class)).toList();

            setBasicAttributes(new AdminCompanyMutateViewModel(
                createBaseViewModel("Админ панель - изменение данных компании"),
                usersViewModel
            ), getLoggedUser(), model);

            model.addAttribute("form", companyForm);

            return "pages/admin/company-edit-page";
        }

        CompanyUpdateDto companyDto = new CompanyUpdateDto(companyForm.getName(), companyForm.getDescription());

        companyService.updateCompany(id, companyDto);
        return "redirect:/admin/companies";
    }
}
