package edu.rut_miit.job_station.controllers.admin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.admin.AdminResumeController;
import com.example.job_station_contracts.input.ResumeForm;
import com.example.job_station_contracts.models.ResumeViewModel;
import com.example.job_station_contracts.models.UserViewModel;
import com.example.job_station_contracts.viewmodels.ResumeCreatePageViewModel;
import com.example.job_station_contracts.viewmodels.admin.AdminEntityTableViewModel;

import edu.rut_miit.job_station.controllers.BaseControllerImpl;
import edu.rut_miit.job_station.dto.resume.ResumeCreateDto;
import edu.rut_miit.job_station.dto.resume.ResumeDto;
import edu.rut_miit.job_station.dto.resume.ResumeUpdateDto;
import edu.rut_miit.job_station.services.ResumeService;
import edu.rut_miit.job_station.services.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/resumes")
public class AdminResumeControllerImpl extends BaseControllerImpl implements AdminResumeController {
    private ResumeService resumeService;
    private UserService userService;

    public AdminResumeControllerImpl(ResumeService resumeService, UserService userService) {
        this.resumeService = resumeService;
        this.userService = userService;
    }

    @Override
    public String resumesPage(Model model) {
        List<ResumeViewModel> resumes = resumeService.findAll().stream().map(r -> modelMapper().map(r, ResumeViewModel.class)).toList();

        setBasicAttributes(new AdminEntityTableViewModel<>(
            createBaseViewModel("Админ панель"),
            resumes
        ), getLoggedUser(), model);

        return "pages/admin/resumes-page";
    }

    @Override
    public String createResumePage(Model model) {
        setBasicAttributes(new ResumeCreatePageViewModel(createBaseViewModel("Админ панель - добавить резюме")), getLoggedUser(), model);
        model.addAttribute("form", new ResumeForm(null, "", "", 0, "", null));
        model.addAttribute("users", userService.findAllUsers().stream().map(u -> modelMapper().map(u, UserViewModel.class)).toList());

        return "pages/admin/resume-create-page";
    }

    @Override
    public String createResume(@Valid @ModelAttribute("form") ResumeForm resumeForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            setBasicAttributes(new ResumeCreatePageViewModel(createBaseViewModel("Админ панель - добавить резюме")), getLoggedUser(), model);
            model.addAttribute("form", resumeForm);
            model.addAttribute("users", userService.findAllUsers().stream().map(u -> modelMapper().map(u, UserViewModel.class)).toList());

            return "pages/admin/resume-create-page";
        }

        Set<String> skills = new HashSet<>();

        for (var skill : resumeForm.getSkills().split(",")) {
            skills.add(skill.trim());
        }

        ResumeCreateDto resumeDto = new ResumeCreateDto(
            resumeForm.getTitle(),
            resumeForm.getContent(),
            resumeForm.getCreatorId(),
            resumeForm.getSalary(),
            List.copyOf(skills),
            new HashMap<>()
        );

        resumeService.createResume(resumeDto);
        return "redirect:/admin/resumes";
    }

    @Override
    public String editResumePage(String id, Model model) {
        ResumeDto resume = resumeService.findById(id);

        setBasicAttributes(new ResumeCreatePageViewModel(createBaseViewModel("Админ панель - добавить резюме")), getLoggedUser(), model);
        model.addAttribute("form", new ResumeForm(resume.getId(), resume.getTitle(), resume.getContent(), resume.getExpectedSalary(), String.join(", ", resume.getSkills()), resume.getCreatorId()));
        model.addAttribute("users", userService.findAllUsers().stream().map(u -> modelMapper().map(u, UserViewModel.class)).toList());

        return "pages/admin/resume-edit-page";
    }

    @Override
    public String editResume(String id, @Valid ResumeForm resumeForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            setBasicAttributes(new ResumeCreatePageViewModel(createBaseViewModel("Админ панель - добавить резюме")), getLoggedUser(), model);
            model.addAttribute("form", resumeForm);
            model.addAttribute("users", userService.findAllUsers().stream().map(u -> modelMapper().map(u, UserViewModel.class)).toList());

            return "pages/admin/resume-edit-page";
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
        return "redirect:/admin/resumes";
    }
}
