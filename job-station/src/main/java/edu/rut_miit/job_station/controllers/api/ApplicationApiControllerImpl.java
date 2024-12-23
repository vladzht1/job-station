package edu.rut_miit.job_station.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.rut_miit.job_station.services.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationApiControllerImpl {
    private ApplicationService applicationService;

    public ApplicationApiControllerImpl(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/check_application")
    public boolean hasUsedAppliedToVacancy(@RequestParam() String vacancyId, @RequestParam() String resumeId) {
        return applicationService.checkUserAppliedToVacancy(vacancyId, resumeId);
    }
}
