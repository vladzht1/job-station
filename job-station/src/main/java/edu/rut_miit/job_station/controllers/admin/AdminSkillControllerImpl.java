package edu.rut_miit.job_station.controllers.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.admin.AdminSkillController;
import com.example.job_station_contracts.input.SkillForm;
import com.example.job_station_contracts.models.SkillViewModel;
import com.example.job_station_contracts.viewmodels.NoInputPageViewModel;
import com.example.job_station_contracts.viewmodels.admin.AdminEntityTableViewModel;

import edu.rut_miit.job_station.controllers.BaseControllerImpl;
import edu.rut_miit.job_station.dto.skill.SkillUpdateDto;
import edu.rut_miit.job_station.entities.SkillCategory;
import edu.rut_miit.job_station.services.SkillService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/skills")
public class AdminSkillControllerImpl extends BaseControllerImpl implements AdminSkillController {
    private SkillService skillService;

    public AdminSkillControllerImpl(SkillService skillService) {
        this.skillService = skillService;
    }

    @Override
    @GetMapping("")
    public String skillsPage(Model model) {
        List<SkillViewModel> skills = skillService
            .findAllSkills()
            .stream()
            .map(u -> modelMapper().map(u, SkillViewModel.class))
            .toList();

        setBasicAttributes(new AdminEntityTableViewModel<>(
            createBaseViewModel("Админ панель: навыки"),
            skills
        ), getLoggedUser(), model);

        return "pages/admin/skills-page";

    }

    @GetMapping("/{id}/edit")
    public String editSkillPage(@PathVariable String id, Model model) {
        setBasicAttributes(new NoInputPageViewModel(
            createBaseViewModel("Админ панель: навыки")
        ), getLoggedUser(), model);

        model.addAttribute("form", new SkillForm(null, ""));

        return "pages/admin/skill-update-page";
    }

    @PostMapping("/{id}/edit")
    public String editSkill(@PathVariable String id, @Valid @ModelAttribute("form") SkillForm skillForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for (var error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            setBasicAttributes(new NoInputPageViewModel(
                createBaseViewModel("Админ панель: навыки")
            ), getLoggedUser(), model);

            model.addAttribute("form", new SkillForm(null, ""));

            return "pages/admin/skill-update-page";
        }

        SkillUpdateDto skillDto = new SkillUpdateDto(id, skillForm.getName(), SkillCategory.NOT_CATEGORIZED);
        skillService.updateSkill(skillDto);

        return "redirect:/admin/skills";
    }
}
