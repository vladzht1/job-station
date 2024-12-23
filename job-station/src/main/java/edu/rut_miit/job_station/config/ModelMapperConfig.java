package edu.rut_miit.job_station.config;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.job_station_contracts.models.ApplicationViewModel;
import com.example.job_station_contracts.models.ResumeViewModel;
import com.example.job_station_contracts.models.VacancyViewModel;

import edu.rut_miit.job_station.dto.application.ApplicationDto;
import edu.rut_miit.job_station.dto.resume.ResumeDto;
import edu.rut_miit.job_station.dto.skill.SkillDto;
import edu.rut_miit.job_station.dto.vacancy.VacancyDto;
import edu.rut_miit.job_station.entities.ApplicationStatus;
import edu.rut_miit.job_station.entities.Skill;
import edu.rut_miit.job_station.entities.SkillCategory;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();

        initialize(mapper);
        return mapper;
    }

    private void initialize(ModelMapper modelMapper) {
        Converter<ApplicationStatus, String> applicationStatusToRusStringConverter = (MappingContext<ApplicationStatus, String> context) -> {
            switch (context.getSource()) {
                case INVITE: return "Приглашение";
                case REJECT: return "Отказ";
                case SEEN: return "Просмотрено";
                default: return "Не просмотрено";
            }
        };

        Converter<SkillCategory, String> skillCategoryToRusStringConverter = (MappingContext<SkillCategory, String> context) -> {
            switch (context.getSource()) {
                case PROGRAMMING_LANGUAGE: return "Язык программирование";
                case TECHNICAL: return "Технический";
                case SOCIAL: return "Социальный";
                default: return "Без категории";
            }
        };

        Converter<List<?>, Integer> listToSizeConverter = (MappingContext<List<?>, Integer> context) -> {
            return context.getSource().size();
        };

        modelMapper.addConverter(applicationStatusToRusStringConverter);
        modelMapper.addConverter(skillCategoryToRusStringConverter);
        modelMapper.addConverter(listToSizeConverter);

        modelMapper
            .createTypeMap(VacancyDto.class, VacancyViewModel.class)
            .addMappings(mapper -> {
                mapper.map(vacancy -> vacancy.getContent(), VacancyViewModel::setDescription);
            });

        modelMapper
            .createTypeMap(ResumeDto.class, ResumeViewModel.class)
            .addMappings(mapper -> {
                mapper.map(resume -> resume.getCreatorId(), ResumeViewModel::setUserId);
                mapper.map(resume -> resume.getCreatorFirstName(), ResumeViewModel::setUserFirstName);
                mapper.map(resume -> resume.getCreatorMiddleName(), ResumeViewModel::setUserMiddleName);
                mapper.map(resume -> resume.getCreatorLastName(), ResumeViewModel::setUserLastName);
            });

        modelMapper
            .createTypeMap(ApplicationDto.class, ApplicationViewModel.class)
            .addMappings(mapper -> {
                mapper.using(applicationStatusToRusStringConverter).map(application -> application.getStatus(), ApplicationViewModel::setStatus);
            });

        modelMapper
            .createTypeMap(Skill.class, SkillDto.class)
            .addMappings(mapper -> {
                mapper.using(skillCategoryToRusStringConverter).map(skill -> skill.getCategory(), SkillDto::setCategory);
                mapper.using(listToSizeConverter).map(skill -> skill.getResumes(), SkillDto::setUsedInResumes);
                mapper.using(listToSizeConverter).map(skill -> skill.getVacancies(), SkillDto::setUsedInVacancies);
            });
    }
}
