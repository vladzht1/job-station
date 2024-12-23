package edu.rut_miit.job_station.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import edu.rut_miit.job_station.dto.resume.ResumeCreateDto;
import edu.rut_miit.job_station.dto.resume.ResumeDto;
import edu.rut_miit.job_station.dto.resume.ResumeUpdateDto;
import edu.rut_miit.job_station.entities.Resume;
import edu.rut_miit.job_station.entities.Skill;
import edu.rut_miit.job_station.entities.User;
import edu.rut_miit.job_station.exceptions.ClientException;
import edu.rut_miit.job_station.misc.SetsUtils;
import edu.rut_miit.job_station.repositories.ResumeRepository;
import edu.rut_miit.job_station.repositories.UserRepository;
import edu.rut_miit.job_station.services.ResumeService;
import edu.rut_miit.job_station.services.SkillService;

@Service
@EnableCaching
public class ResumeServiceImpl implements ResumeService {
    private SkillService skillService;

    private ResumeRepository resumeRepository;
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    @Cacheable("resumes")
    public List<ResumeDto> findAll() {
        return resumeRepository
            .findAll()
            .stream()
            .map(r -> modelMapper.map(r, ResumeDto.class))
            .toList();
    }

    @Override
    public List<ResumeDto> findAllResumesByUserId(String userId) {
        return resumeRepository
            .findAllByUserIdAndActive(userId, true)
            .stream()
            .map(r -> modelMapper.map(r, ResumeDto.class))
            .toList();
    }

    @Override
    public List<ResumeDto> findAllInactiveResumesByUserId(String userId) {
        return resumeRepository
            .findAllByUserIdAndActive(userId, false)
            .stream()
            .map(r -> modelMapper.map(r, ResumeDto.class))
            .toList();
    }

    @Override
    public ResumeDto findById(String id) {
        return modelMapper.map(resumeRepository.findById(id), ResumeDto.class);
    }

    @Override
    public long count() {
        return resumeRepository.count();
    }

    @Override
    @CacheEvict(cacheNames = "resumes", allEntries = true)
    public ResumeDto createResume(ResumeCreateDto resumeDto) {
        User creator = userRepository.findById(resumeDto.getCreatorId()).orElseThrow(() -> new ClientException.NotFoundException("Creator was not found"));

        List<Resume> userResumes = resumeRepository.findAllByUserId(creator.getId());

        for (var resume : userResumes) {
            if (resume.getTitle().equals(resumeDto.getTitle())) {
                throw new ClientException.ConflictException("Resume with such job title already exists");
            }
        }

        Set<Skill> skills = skillService.findOrCreateSkillsFrom(resumeDto.getSkills());

        Resume resume = new Resume(resumeDto.getTitle(), resumeDto.getContent(), creator, resumeDto.getExpectedSalary(), skills, new ArrayList<>());
        return modelMapper.map(resumeRepository.save(resume), ResumeDto.class);
    }

    @Override
    @CacheEvict(cacheNames = "vacancies", allEntries = true)
    public ResumeDto updateResume(ResumeUpdateDto resumeDto) {
        Resume resume = resumeRepository.findById(resumeDto.getId()).orElseThrow(() -> new ClientException.NotFoundException("Resume was not found"));

        List<Resume> userResumes = resumeRepository.findAllByUserId(resume.getCreator().getId());

        for (var userResume : userResumes) {
            // Skipping comparing to self
            if (userResume.getId() == resumeDto.getId()) {
                continue;
            }

            // Attempt to create resume with the job title already existing among the user resumes
            if (userResume.getTitle().equals(resumeDto.getTitle())) {
                System.out.println(userResume.getId());
                System.out.println(resumeDto.getId());
                throw new ClientException.ConflictException("Resume with such job title already exists");
            }
        }

        if (resumeDto.getTitle() != null) resume.setTitle(resumeDto.getTitle());
        if (resumeDto.getContent() != null) resume.setContent(resumeDto.getContent());
        if (resumeDto.getExpectedSalary() != 0) resume.setExpectedSalary(resumeDto.getExpectedSalary());

        // Intersection length must differ from current vacancy to find out we have new skills provided
        if (
            SetsUtils.calculateIntersection(
                // Incoming skills ids
                resumeDto.getSkills().stream().toList(),

                // Ids of skills stored in the vacancy
                resume.getSkills().stream().map(s -> s.getId()).toList()
            ).size() != resume.getSkills().size())
        {
            Set<Skill> skills = skillService.findOrCreateSkillsFrom(resumeDto.getSkills());
            resume.setSkills(skills);
        }

        return modelMapper.map(resumeRepository.update(resume), ResumeDto.class);
    }

    @Autowired
    public void setResumeRepository(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
