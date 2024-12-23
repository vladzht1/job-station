package edu.rut_miit.job_station.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.ResumeController;
import com.example.job_station_contracts.input.ResumeForm;
import com.example.job_station_contracts.models.ResumeViewModel;
import com.example.job_station_contracts.models.UserViewModel;
import com.example.job_station_contracts.models.VacancyViewModel;
import com.example.job_station_contracts.viewmodels.ResumeCreatePageViewModel;
import com.example.job_station_contracts.viewmodels.ResumePageViewModel;
import com.example.job_station_contracts.viewmodels.ResumeUpdatePageViewModel;
import com.example.job_station_contracts.viewmodels.UserResumesPageViewModel;

import edu.rut_miit.job_station.dto.resume.ResumeCreateDto;
import edu.rut_miit.job_station.dto.resume.ResumeDto;
import edu.rut_miit.job_station.dto.resume.ResumeUpdateDto;
import edu.rut_miit.job_station.dto.user.UserDto;
import edu.rut_miit.job_station.services.ResumeService;
import edu.rut_miit.job_station.services.UserService;
import edu.rut_miit.job_station.services.VacancyService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/resumes")
public class ResumeControllerImpl extends BaseControllerImpl implements ResumeController {
    private ResumeService resumeService;
    private VacancyService vacancyService;
    private UserService userService;

    public ResumeControllerImpl(ResumeService resumeService, VacancyService vacancyService, UserService userService) {
        this.resumeService = resumeService;
        this.vacancyService = vacancyService;
        this.userService = userService;
    }

    @Override
    @GetMapping()
    public String userResumesPage(Model model, Principal principal) {
        UserDto loggedUser = getLoggedUser();

        List<ResumeViewModel> resumes = resumeService
            .findAllResumesByUserId(loggedUser.getId())
            .stream()
            .map(r -> modelMapper().map(r, ResumeViewModel.class))
            .toList();

        List<VacancyViewModel> recommendedVacancies = vacancyService
            .findTop5VacanciesForUser(loggedUser.getId())
            .stream()
            .map(r -> modelMapper().map(r, VacancyViewModel.class))
            .toList();

        setBasicAttributes(new UserResumesPageViewModel(
            createBaseViewModel("Мои резюме"),
            resumes,
            recommendedVacancies
        ), loggedUser, model);

        return "pages/resumes-page";
    }

    @Override
    @GetMapping("/{id}")
    public String resumePage(@PathVariable String id, Model model, Principal principal) {
        ResumeDto resume = resumeService.findById(id);
        UserDto user = userService.findUserById(resume.getCreatorId());

        ResumeViewModel resumeViewModel = modelMapper().map(resume, ResumeViewModel.class);
        UserViewModel userViewModel = modelMapper().map(user, UserViewModel.class);

        List<VacancyViewModel> recommendedVacancies = vacancyService
            .findTop5VacanciesForUser(getLoggedUser().getId())
            .stream()
            .map(r -> modelMapper().map(r, VacancyViewModel.class))
            .toList();

        setBasicAttributes(new ResumePageViewModel(
            createBaseViewModel(resumeViewModel.getTitle()),
            userViewModel,
            resumeViewModel,
            recommendedVacancies
        ), getLoggedUser(), model);

        return "pages/resume-page";
    }

    @Override
    @GetMapping("/new")
    public String createResumePage(Model model) {
        setBasicAttributes(new ResumeCreatePageViewModel(createBaseViewModel("Добавить резюме")), getLoggedUser(), model);
        model.addAttribute("form", new ResumeForm(null, "", "", 0, "", null));

        return "pages/resume-create-page";
    }

    @Override
    @PostMapping("/new")
    public String createResume(@Valid @ModelAttribute("form") ResumeForm resumeForm, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            setBasicAttributes(new ResumeCreatePageViewModel(createBaseViewModel("Добавить резюме")), getLoggedUser(), model);
            model.addAttribute("form", resumeForm);

            return "pages/resume-create-page";
        }

        Set<String> skills = new HashSet<>();

        for (String skill : resumeForm.getSkills().split(",")) {
            skills.add(skill.trim());
        }

        ResumeCreateDto resumeDto = new ResumeCreateDto(
            resumeForm.getTitle(),
            resumeForm.getContent(),
            getLoggedUser().getId(),
            resumeForm.getSalary(),
            List.copyOf(skills),
            new HashMap<>()
        );

        resumeService.createResume(resumeDto);
        return "redirect:/resumes";
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editResumePage(@PathVariable String id, Model model) {
        ResumeDto resume = resumeService.findById(id);

        setBasicAttributes(new ResumeUpdatePageViewModel(createBaseViewModel("Обновление резюме")), getLoggedUser(), model);
        model.addAttribute("form", new ResumeForm(resume.getId(), resume.getTitle(), resume.getContent(), resume.getExpectedSalary(), String.join(", ", resume.getSkills()), resume.getCreatorId()));

        return "pages/resume-update-page";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String updateResume(@PathVariable String id, @Valid @ModelAttribute("form") ResumeForm resumeForm, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            setBasicAttributes(new ResumeUpdatePageViewModel(createBaseViewModel("Обновление резюме")), getLoggedUser(), model);
            model.addAttribute("form", resumeForm);

            return "pages/resume-update-page";
        }

        Set<String> skills = new HashSet<>();

        for (var skill : resumeForm.getSkills().split(",")) {
            skills.add(skill.trim());
        }

        ResumeUpdateDto resumeDto = new ResumeUpdateDto(
            id,
            resumeForm.getTitle(),
            resumeForm.getContent(),
            resumeForm.getSalary(),
            List.copyOf(skills)
        );

        resumeService.updateResume(resumeDto);
        return "redirect:/resumes/" + id;
    }
}
