package edu.rut_miit.job_station.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.job_station_contracts.controllers.admin.AdminVacancyController;
import com.example.job_station_contracts.input.VacancyForm;

import edu.rut_miit.job_station.controllers.BaseControllerImpl;
import jakarta.validation.Valid;

@Controller
public class AdminVacancyControllerImpl extends BaseControllerImpl implements AdminVacancyController {

    @Override
    public String usersPage(Model model) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usersPage'");
    }

    @Override
    public String editVacancyPage(String id, Model model) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editVacancyPage'");
    }

    @Override
    public String editVacancy(String id, @Valid VacancyForm vacancyForm, BindingResult bindingResult, Model model) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editVacancy'");
    }

}
