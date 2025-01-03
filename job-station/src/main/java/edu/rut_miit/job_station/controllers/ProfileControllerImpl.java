package edu.rut_miit.job_station.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.ProfileController;
import com.example.job_station_contracts.models.ResumeViewModel;
import com.example.job_station_contracts.models.UserViewModel;
import com.example.job_station_contracts.viewmodels.ProfilePageViewModel;

import edu.rut_miit.job_station.services.ResumeService;

@Controller
@RequestMapping("/profile")
public class ProfileControllerImpl extends BaseControllerImpl implements ProfileController {
    private static final Logger logger = LogManager.getLogger(ProfileControllerImpl.class);

    private ResumeService resumeService;

    public ProfileControllerImpl(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @Override
    @GetMapping("")
    public String profilePage(Model model) {
        var loggedUser = getLoggedUser();

        logger.info("user " + loggedUser.getUsername() + " opened profile page");

        setBasicAttributes(new ProfilePageViewModel(
            createBaseViewModel("Мой профиль"),
            modelMapper().map(loggedUser, UserViewModel.class),
            resumeService
                .findAllResumesByUserId(loggedUser.getId())
                .stream()
                .map(r -> modelMapper().map(r, ResumeViewModel.class))
                .toList()
        ), getLoggedUser(), model);

        return "pages/profile-page";
    }
}
