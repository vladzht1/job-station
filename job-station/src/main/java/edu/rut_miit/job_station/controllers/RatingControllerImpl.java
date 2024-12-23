package edu.rut_miit.job_station.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.RatingController;
import com.example.job_station_contracts.models.CompanyRatingViewModel;
import com.example.job_station_contracts.models.SkillRatingViewModel;
import com.example.job_station_contracts.viewmodels.RatingPageViewModel;

import edu.rut_miit.job_station.services.CompanyService;
import edu.rut_miit.job_station.services.SkillService;

@Controller
@RequestMapping("/rating")
public class RatingControllerImpl extends BaseControllerImpl implements RatingController {
    private CompanyService companyService;
    private SkillService skillService;

    public RatingControllerImpl(CompanyService companyService, SkillService skillService) {
        this.companyService = companyService;
        this.skillService = skillService;
    }

    @Override
    @GetMapping("")
    public String ratingPage(Model model) {
        List<CompanyRatingViewModel> companies = companyService
            .findMostRankedCompanies()
            .stream()
            .map(c -> modelMapper().map(c, CompanyRatingViewModel.class))
            .toList();

        List<SkillRatingViewModel> skills = skillService
            .findMostPopularSkills()
            .stream()
            .map(s -> modelMapper().map(s, SkillRatingViewModel.class))
            .toList();

        setBasicAttributes(new RatingPageViewModel(
            createBaseViewModel("Рейтинг"),
            companies,
            skills
        ), getLoggedUser(), model);

        return "pages/rating-page";
    }
}
