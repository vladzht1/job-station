package com.example.job_station_contracts.viewmodels;

import java.util.List;

import com.example.job_station_contracts.models.VacancyViewModel;

public class VacancyPageViewModel {
    public BaseViewModel base;
    public VacancyViewModel vacancy;
    public List<VacancyViewModel> alikeVacancies;
    public List<VacancyViewModel> userRecommendedVacancies;

    public VacancyPageViewModel(BaseViewModel base, VacancyViewModel vacancy, List<VacancyViewModel> alikeVacancies, List<VacancyViewModel> userRecommendedVacancies) {
        this.base = base;
        this.vacancy = vacancy;
        this.alikeVacancies = alikeVacancies;
        this.userRecommendedVacancies = userRecommendedVacancies;
    }
}
