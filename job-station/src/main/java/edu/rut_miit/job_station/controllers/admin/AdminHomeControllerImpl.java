package edu.rut_miit.job_station.controllers.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.job_station_contracts.controllers.admin.AdminHomeController;
import com.example.job_station_contracts.models.admin.AdminTableInfoViewModel;
import com.example.job_station_contracts.viewmodels.admin.AdminHomePageViewModel;

import edu.rut_miit.job_station.controllers.BaseControllerImpl;
import edu.rut_miit.job_station.services.CompanyService;
import edu.rut_miit.job_station.services.ResumeService;
import edu.rut_miit.job_station.services.SkillService;
import edu.rut_miit.job_station.services.UserService;
import edu.rut_miit.job_station.services.VacancyService;

@Controller
@RequestMapping("/admin")
public class AdminHomeControllerImpl extends BaseControllerImpl implements AdminHomeController {
    private UserService userService;
    private CompanyService companyService;
    private ResumeService resumeService;
    private VacancyService vacancyService;
    private SkillService skillService;

    public AdminHomeControllerImpl(
        UserService userService,
        CompanyService companyService,
        ResumeService resumeService,
        VacancyService vacancyService,
        SkillService skillService
    ) {
        this.userService = userService;
        this.companyService = companyService;
        this.resumeService = resumeService;
        this.vacancyService = vacancyService;
        this.skillService = skillService;
    }

    @Override
    public String indexPage(Model model) {
        List<AdminTableInfoViewModel> tablesInfo = new ArrayList<>();

        tablesInfo.add(new AdminTableInfoViewModel("/admin/users", "Пользователи", userService.count()));
        tablesInfo.add(new AdminTableInfoViewModel("/admin/companies", "Компании", companyService.count()));
        tablesInfo.add(new AdminTableInfoViewModel("/admin/resumes", "Резюме", resumeService.count()));
        tablesInfo.add(new AdminTableInfoViewModel("/admin/vacancies", "Вакансии", vacancyService.count()));
        tablesInfo.add(new AdminTableInfoViewModel("/admin/skills", "Навыки", skillService.count()));

        setBasicAttributes(new AdminHomePageViewModel<>(createBaseViewModel("Админ панель"), tablesInfo), getLoggedUser(), model);

        return "pages/admin/home-page.html";
    }
}
