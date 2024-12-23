package edu.rut_miit.job_station.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.ApplicationController;
import com.example.job_station_contracts.models.ApplicationViewModel;
import com.example.job_station_contracts.viewmodels.UserApplicationsPageViewModel;

import edu.rut_miit.job_station.services.ApplicationService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/applications")
public class ApplicationControllerImpl extends BaseControllerImpl implements ApplicationController {
    private ApplicationService applicationService;

    public ApplicationControllerImpl(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    @GetMapping("")
    public String userApplicationsPage(Model model) {
        List<ApplicationViewModel> applications = applicationService
            .findAllUserApplications(getLoggedUser().getId())
            .stream()
            .map(a -> modelMapper().map(a, ApplicationViewModel.class))
            .toList();

        setBasicAttributes(new UserApplicationsPageViewModel(
            createBaseViewModel("Отклики"),
            applications
        ), getLoggedUser(), model);

        return "pages/applications-page";
    }

    @Override
    @PostMapping("/{id}/accept")
    public String acceptReply(@PathVariable String id, HttpServletRequest request) {
        applicationService.accept(id);

        return getPreviousPageByRequest(request).orElse("/");
    }

    @Override
    @PostMapping("/{id}/deny")
    public String denyReply(@PathVariable String id, HttpServletRequest request) {
        applicationService.deny(id);

        return getPreviousPageByRequest(request).orElse("/");
    }
}
