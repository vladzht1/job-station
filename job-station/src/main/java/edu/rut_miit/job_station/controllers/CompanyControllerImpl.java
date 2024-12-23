package edu.rut_miit.job_station.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.CompanyController;
import com.example.job_station_contracts.input.CompanyForm;
import com.example.job_station_contracts.input.VacancyForm;
import com.example.job_station_contracts.models.ApplicationViewModel;
import com.example.job_station_contracts.models.CompanyViewModel;
import com.example.job_station_contracts.models.VacancyViewModel;
import com.example.job_station_contracts.viewmodels.CompaniesPageViewModel;
import com.example.job_station_contracts.viewmodels.CompanyCreatePageViewModel;
import com.example.job_station_contracts.viewmodels.CompanyPageViewModel;
import com.example.job_station_contracts.viewmodels.NoInputPageViewModel;
import com.example.job_station_contracts.viewmodels.VacancyCreatePageViewModel;

import edu.rut_miit.job_station.dto.company.CompanyCreateDto;
import edu.rut_miit.job_station.dto.company.CompanyDto;
import edu.rut_miit.job_station.dto.company.CompanyUpdateDto;
import edu.rut_miit.job_station.dto.vacancy.VacancyCreateDto;
import edu.rut_miit.job_station.services.ApplicationService;
import edu.rut_miit.job_station.services.CompanyService;
import edu.rut_miit.job_station.services.VacancyService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/companies")
public class CompanyControllerImpl extends BaseControllerImpl implements CompanyController {
    private ApplicationService applicationService;
    private CompanyService companyService;
    private VacancyService vacancyService;

    public CompanyControllerImpl(ApplicationService applicationService, CompanyService companyService, VacancyService vacancyService) {
        this.applicationService = applicationService;
        this.companyService = companyService;
        this.vacancyService = vacancyService;
    }

    @Override
    @GetMapping("")
    public String companiesPage(Model model) {
        var loggedUser = getLoggedUser();

        List<CompanyViewModel> companies = companyService
            .findCompaniesByCreatorId(loggedUser.getId())
            .stream()
            .map(c -> modelMapper().map(c, CompanyViewModel.class))
            .toList();

        setBasicAttributes(new CompaniesPageViewModel(createBaseViewModel("Компании"), companies), loggedUser, model);
        return "pages/companies-page";
    }

    @Override
    @GetMapping("/{id}")
    public String companyPage(@PathVariable String id, Model model) {
        CompanyViewModel company = modelMapper().map(companyService.findCompanyById(id), CompanyViewModel.class);

        List<VacancyViewModel> vacancies = vacancyService
            .findAllVacanciesByCompanyId(id)
            .stream()
            .map(v -> modelMapper().map(v, VacancyViewModel.class))
            .toList();

        setBasicAttributes(new CompanyPageViewModel(createBaseViewModel(company.getName()), company, vacancies), getLoggedUser(), model);

        var loggedUser = getLoggedUser();

        if (loggedUser != null && loggedUser.getId() == company.getCreator().getId()) {
            model.addAttribute("applications",
                applicationService
                    .findActiveApplicationsToCompany(id)
                    .stream()
                    .map(a -> modelMapper().map(a, ApplicationViewModel.class))
                    .toList()
            );
        }

        return "pages/company-page";
    }

    @Override
    @GetMapping("/new")
    public String createCompanyPage(Model model) {
        setBasicAttributes(new CompanyCreatePageViewModel(createBaseViewModel("Создать компанию")), getLoggedUser(), model);
        model.addAttribute("form", new CompanyForm(null, "", "", ""));

        return "pages/company-create-page";
    }

    @Override
    @PostMapping("/new")
    public String createCompany(@Valid @ModelAttribute("form") CompanyForm companyForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            setBasicAttributes(new CompanyCreatePageViewModel(createBaseViewModel("Создать компанию")), getLoggedUser(), model);
            model.addAttribute("form", companyForm);

            return "pages/company-create-page";
        }

        companyService.registerCompany(new CompanyCreateDto(companyForm.getName(), companyForm.getDescription()), getLoggedUser().getId());

        return "redirect:/companies";
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editCompanyPage(@PathVariable String id, Model model) {
        CompanyDto company = companyService.findCompanyById(id);

        setBasicAttributes(new NoInputPageViewModel(createBaseViewModel("Обновить данные компании")), getLoggedUser(), model);
        model.addAttribute("form", company);

        return "pages/company-edit-page";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String editCompany(@PathVariable String id, @Valid @ModelAttribute("form") CompanyForm companyForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            setBasicAttributes(new NoInputPageViewModel(createBaseViewModel("Обновить данные компании")), getLoggedUser(), model);
            model.addAttribute("form", companyForm);

            return "pages/company-edit-page";
        }

        CompanyUpdateDto companyDto = new CompanyUpdateDto(companyForm.getName(), companyForm.getDescription());

        companyService.updateCompany(id, companyDto);
        return "redirect:/companies/" + id;
    }

    @Override
    @GetMapping("/{id}/vacancies/new")
    public String createVacancyPage(@PathVariable String id, Model model) {
        setBasicAttributes(new VacancyCreatePageViewModel(
            createBaseViewModel("Вакансия")
        ), getLoggedUser(), model);

        model.addAttribute("form", new VacancyForm("", id, 0, "", ""));

        return "pages/vacancy-create-page";
    }

    @Override
    @PostMapping("/{id}/vacancies/new")
    public String createVacancy(@PathVariable String id, @Valid @ModelAttribute("form") VacancyForm vacancyForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            setBasicAttributes(new VacancyCreatePageViewModel(
                createBaseViewModel("Вакансия")
            ), getLoggedUser(), model);

            model.addAttribute("form", vacancyForm);

            return "pages/vacancy-create-page";
        }

        List<String> skills = new ArrayList<>();

        for (var skill : vacancyForm.getSkills().split(",")) {
            skills.add(skill.trim());
        }

        VacancyCreateDto vacancyDto = new VacancyCreateDto(
            vacancyForm.getTitle(),
            vacancyForm.getContent(),
            id,
            vacancyForm.getOfferedSalary(),
            skills
        );

        var vacancy = vacancyService.addVacancy(vacancyDto);
        return "redirect:/vacancies/" + vacancy.getId();
    }
}
