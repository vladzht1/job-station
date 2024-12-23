package edu.rut_miit.job_station.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import edu.rut_miit.job_station.dto.vacancy.VacancyCreateDto;
import edu.rut_miit.job_station.dto.vacancy.VacancyDto;
import edu.rut_miit.job_station.dto.vacancy.VacancyUpdateDto;
import edu.rut_miit.job_station.entities.Company;
import edu.rut_miit.job_station.entities.Resume;
import edu.rut_miit.job_station.entities.Skill;
import edu.rut_miit.job_station.entities.Vacancy;
import edu.rut_miit.job_station.exceptions.ClientException;
import edu.rut_miit.job_station.misc.SetsUtils;
import edu.rut_miit.job_station.repositories.CompanyRepository;
import edu.rut_miit.job_station.repositories.ResumeRepository;
import edu.rut_miit.job_station.repositories.VacancyRepository;
import edu.rut_miit.job_station.services.SkillService;
import edu.rut_miit.job_station.services.VacancyService;

@Service
@EnableCaching
public class VacancyServiceImpl implements VacancyService {
    private SkillService skillService;

    private VacancyRepository vacancyRepository;
    private CompanyRepository companyRepository;
    private ResumeRepository resumeRepository;

    private ModelMapper modelMapper;

    @Override
    @Cacheable("vacancies")
    public List<VacancyDto> findAll() {
        try {
            Thread.sleep(123);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return vacancyRepository
            .findAll()
            .stream()
            .map(v -> modelMapper.map(v, VacancyDto.class))
            .toList();
    }

    @Override
    public VacancyDto findById(String id) {
        Vacancy vacancy = vacancyRepository
            .findById(id)
            .orElseThrow(() -> new ClientException.NotFoundException("Vacancy was not found"));

        return modelMapper.map(vacancy, VacancyDto.class);
    }

    @Override
    public List<VacancyDto> findAlikeVacancies(String vacancyId, int limit) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow(() -> new ClientException.NotAllowedException("Vacancy was not found"));

        return calculateMostSuitableVacanciesBySkills(List.copyOf(vacancy.getSkills()), limit)
            .stream()
            .map(v -> modelMapper.map(v, VacancyDto.class))
            .toList();
    }

    @Override
    public List<VacancyDto> findAllVacanciesByCompanyId(String companyId) {
        return vacancyRepository
            .findAllByCompanyId(companyId)
            .stream()
            .map(v -> modelMapper.map(v, VacancyDto.class))
            .toList();
    }

    @Override
    @Cacheable("top_user_vacancies")
    public List<VacancyDto> findTop5VacanciesForUser(String userId) {
        return findRecommendedVacanciesForUser(userId, 5);
    }

    @Override
    public List<VacancyDto> findRecommendedVacanciesForUser(String userId, int limit) {
        List<Resume> userResumes = resumeRepository.findAllByUserId(userId);

        Set<Skill> userSkills = new HashSet<>();

        for (var resume : userResumes) {
            for (var skill : resume.getSkills()) {
                userSkills.add(skill);
            }
        }

        return calculateMostSuitableVacanciesBySkills(List.copyOf(userSkills), limit)
            .stream()
            .map(v -> modelMapper.map(v, VacancyDto.class))
            .toList();
    }

    @Override
    public long count() {
        return vacancyRepository.count();
    }

    @Override
    @CacheEvict(cacheNames = "vacancies", allEntries = true)
    public VacancyDto addVacancy(VacancyCreateDto vacancyDto) {
        Company company = companyRepository.findById(vacancyDto.getCompanyId()).orElseThrow(() -> new ClientException.NotFoundException("Company was not found"));

        Set<Skill> skills = skillService.findOrCreateSkillsFrom(vacancyDto.getSkills());

        Vacancy vacancy = new Vacancy(
            vacancyDto.getTitle(),
            vacancyDto.getContent(),
            company,
            vacancyDto.getOfferedSalary(),
            skills,
            new ArrayList<>()
        );

        return modelMapper.map(vacancyRepository.save(vacancy), VacancyDto.class);
    }

    @Override
    @CacheEvict(cacheNames = "vacancies", allEntries = true)
    public VacancyDto updateVacancy(VacancyUpdateDto vacancyDto) {
        Vacancy vacancy = vacancyRepository
            .findById(vacancyDto.getId())
            .orElseThrow(() -> new ClientException.NotFoundException("Vacancy was not found"));

        if (vacancyDto.getTitle() != null) vacancy.setTitle(vacancyDto.getTitle());
        if (vacancyDto.getContent() != null) vacancy.setContent(vacancyDto.getContent());
        if (vacancyDto.getOfferedSalary() != 0) vacancy.setOfferedSalary(vacancyDto.getOfferedSalary());

        // Intersection length must differ from current vacancy to find out we have new skills provided
        if (
            SetsUtils.calculateIntersection(
                // Incoming skills ids
                vacancyDto.getSkills().stream().toList(),

                // Ids of skills stored in the vacancy
                vacancy.getSkills().stream().map(s -> s.getId()).toList()
            ).size() != vacancy.getSkills().size())
        {
            Set<Skill> skills = skillService.findOrCreateSkillsFrom(List.copyOf(vacancyDto.getSkills()));
            vacancy.setSkills(skills);
        }

        return modelMapper.map(vacancyRepository.update(vacancy), VacancyDto.class);
    }

    @Override
    @CacheEvict(cacheNames = "vacancies", allEntries = true)
    public void activateVacancy(String id) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(() -> new ClientException.NotFoundException("Vacancy was not found"));

        vacancy.activate();
        vacancyRepository.update(vacancy);
    }

    @Override
    @CacheEvict(cacheNames = "vacancies", allEntries = true)
    public void deactivateVacancy(String id) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(() -> new ClientException.NotFoundException("Vacancy was not found"));

        vacancy.deactivate();
        vacancyRepository.update(vacancy);
    }

    private List<Vacancy> calculateMostSuitableVacanciesBySkills(List<Skill> skills, int limit) {
        List<Vacancy> recommendedVacancies = vacancyRepository.findBySkills(skills);

        // Before we return vacancies, we must filter top N most suitable ones by skills (the more skills included, the better)
        // Appearances - Vacancy
        NavigableMap<Integer, Vacancy> recommendedVacanciesBySkillIntersection = new TreeMap<>(Comparator.reverseOrder());

        for (var vacancy : recommendedVacancies) {
            int intersections = SetsUtils.calculateIntersection(List.copyOf(vacancy.getSkills()), List.copyOf(skills)).size();
            recommendedVacanciesBySkillIntersection.put(intersections, vacancy);
        }

        int amount = Math.min(limit, recommendedVacanciesBySkillIntersection.size());
        List<Vacancy> vacancies = new ArrayList<>(amount);

        for (int i = 0; i < amount; i++) {
            vacancies.add(recommendedVacanciesBySkillIntersection.pollFirstEntry().getValue());
        }

        return vacancies;
    }

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setVacancyRepository(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setResumeRepository(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
