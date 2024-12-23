package edu.rut_miit.job_station.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.HomeController;
import com.example.job_station_contracts.models.VacancyViewModel;
import com.example.job_station_contracts.viewmodels.HomeViewModel;

import edu.rut_miit.job_station.services.VacancyService;

@Controller()
@RequestMapping("/")
public class HomeControllerImpl extends BaseControllerImpl implements HomeController {
    private VacancyService vacancyService;

    public HomeControllerImpl(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping()
    @Override
    public String homePage(Model model) {
        var loggedUser = getLoggedUser();

        List<VacancyViewModel> vacancies = (
            loggedUser != null
            ? vacancyService.findRecommendedVacanciesForUser(loggedUser.getId(), 100)
            : vacancyService.findAll()
        )
            .stream()
            .map(vacancy -> modelMapper().map(vacancy, VacancyViewModel.class))
            .toList();

        List<VacancyViewModel> recommendedVacancies = loggedUser != null
            ? vacancyService.findTop5VacanciesForUser(loggedUser.getId())
                .stream()
                .map(vacancy -> modelMapper().map(vacancy, VacancyViewModel.class))
                .toList()
            : null;

        setBasicAttributes(new HomeViewModel(
            createBaseViewModel("Главная"),
            vacancies,
            recommendedVacancies
        ), getLoggedUser(), model);

        return "pages/home-page";
    }
}
