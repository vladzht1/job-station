package edu.rut_miit.job_station.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.VacancyController;
import com.example.job_station_contracts.input.ApplicationForm;
import com.example.job_station_contracts.models.ResumeViewModel;
import com.example.job_station_contracts.models.VacancyViewModel;
import com.example.job_station_contracts.viewmodels.VacancyApplyPageViewModel;
import com.example.job_station_contracts.viewmodels.VacancyPageViewModel;

import edu.rut_miit.job_station.dto.vacancy.VacancyDto;
import edu.rut_miit.job_station.services.ApplicationService;
import edu.rut_miit.job_station.services.ResumeService;
import edu.rut_miit.job_station.services.VacancyService;

@Controller()
@RequestMapping("/vacancies")
public class VacancyControllerImpl extends BaseControllerImpl implements VacancyController {
    private ApplicationService applicationService;
    private VacancyService vacancyService;
    private ResumeService resumeService;

    public VacancyControllerImpl(ApplicationService applicationService, VacancyService vacancyService, ResumeService resumeService) {
        this.applicationService = applicationService;
        this.vacancyService = vacancyService;
        this.resumeService = resumeService;
    }

    @Override
    @GetMapping("/{id}")
    public String vacancyPage(@PathVariable String id, Model model) {
        VacancyDto vacancy = vacancyService.findById(id);
        var loggedUser = getLoggedUser();

        List<VacancyViewModel> alikeVacancies = vacancyService.findAlikeVacancies(vacancy.getId(), 6)
            .stream()
            .map(v -> modelMapper().map(v, VacancyViewModel.class))
            .toList();

        List<VacancyViewModel> recommendedVacancies = loggedUser != null
            ? vacancyService.findTop5VacanciesForUser(loggedUser.getId())
                .stream()
                .map(v -> modelMapper().map(v, VacancyViewModel.class))
                .toList()
            : null;

        setBasicAttributes(new VacancyPageViewModel(
            createBaseViewModel(vacancy.getTitle()),
            modelMapper().map(vacancy, VacancyViewModel.class),
            alikeVacancies,
            recommendedVacancies
        ), getLoggedUser(), model);

        return "pages/vacancy-page";
    }

    @Override
    @GetMapping("/{id}/apply")
    public String applyForVacancyPage(@PathVariable() String id, Model model) {
        var loggedUser = getLoggedUser();

        VacancyViewModel vacancy = modelMapper().map(vacancyService.findById(id), VacancyViewModel.class);

        List<ResumeViewModel> resumes = resumeService
            .findAllResumesByUserId(loggedUser.getId())
            .stream()
            .map(r -> modelMapper().map(r, ResumeViewModel.class))
            .toList();

        setBasicAttributes(new VacancyApplyPageViewModel(createBaseViewModel("Отправить отклик на вакансию"), vacancy), getLoggedUser(), model);
        model.addAttribute("resumes", resumes);

        return "pages/vacancy-apply-page";
    }

    @Override
    @PostMapping("/{vacancyId}/apply")
    public String applyForVacancy(@PathVariable String vacancyId, @ModelAttribute("form") ApplicationForm applicationForm, Model model) {
        applicationService.applyToVacancy(applicationForm.getVacancyId(), applicationForm.getResumeId());
        return "redirect:/vacancies/" + vacancyId;
    }
}
