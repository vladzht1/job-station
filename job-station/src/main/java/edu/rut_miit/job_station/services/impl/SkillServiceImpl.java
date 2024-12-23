package edu.rut_miit.job_station.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.rut_miit.job_station.dto.skill.SkillDto;
import edu.rut_miit.job_station.dto.skill.SkillRatingDto;
import edu.rut_miit.job_station.dto.skill.SkillUpdateDto;
import edu.rut_miit.job_station.entities.Skill;
import edu.rut_miit.job_station.entities.SkillCategory;
import edu.rut_miit.job_station.exceptions.ClientException;
import edu.rut_miit.job_station.repositories.SkillRepository;
import edu.rut_miit.job_station.services.SkillService;

@Service
public class SkillServiceImpl implements SkillService {
    private SkillRepository skillRepository;

    private ModelMapper modelMapper;

    @Override
    public List<SkillDto> findAllSkills() {
        return skillRepository
            .findAll()
            .stream()
            .map(s -> modelMapper.map(s, SkillDto.class))
            .toList();
    }

    @Override
    public List<SkillDto> findSkillsByCategory(SkillCategory category) {
        return skillRepository.findByCategory(category).stream().map(s -> modelMapper.map(s, SkillDto.class)).toList();
    }

    @Override
    public List<SkillRatingDto> findMostPopularSkills() {
        return skillRepository.findSortedByCompaniesDemands();
    }

    @Override
    public Set<Skill> findOrCreateSkillsFrom(List<String> skillNames) {
        Set<Skill> skills = new HashSet<>();

        for (var skillName : skillNames) {
            Optional<Skill> skillOpt = skillRepository.findByName(skillName);

            Skill skill;

            if (skillOpt.isPresent()) {
                skill = skillOpt.get();
            } else {
                skill = skillRepository.save(new Skill(skillName));
            }

            skills.add(skill);
        }

        return skills;
    }

    @Override
    public long count() {
        return skillRepository.count();
    }

    @Override
    public SkillDto createSkill(String title) {
        return createSkill(title, SkillCategory.NOT_CATEGORIZED);
    }

    @Override
    public SkillDto createSkill(String title, SkillCategory category) {
        Skill skill = new Skill(title, category);

        return modelMapper.map(skillRepository.save(skill), SkillDto.class);
    }

    @Override
    public SkillDto updateSkill(SkillUpdateDto skillDto) {
        Optional<Skill> skillOpt = skillRepository.findById(skillDto.getId());

        if (skillOpt.isEmpty()) {
            throw new ClientException.NotFoundException("Skill was not found");
        }

        Skill skill = skillOpt.get();

        if (skillDto.getName() != null) skill.setName(skillDto.getName());
        if (skillDto.getCategory() != null) skill.setCategory(skillDto.getCategory());

        return modelMapper.map(skillRepository.save(skill), SkillDto.class);
    }

    @Autowired
    public void setSkillRepository(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
