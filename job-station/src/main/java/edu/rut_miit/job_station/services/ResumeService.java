package edu.rut_miit.job_station.services;

import java.util.List;

import edu.rut_miit.job_station.dto.resume.ResumeCreateDto;
import edu.rut_miit.job_station.dto.resume.ResumeDto;
import edu.rut_miit.job_station.dto.resume.ResumeUpdateDto;

public interface ResumeService {
    List<ResumeDto> findAll();
    List<ResumeDto> findAllResumesByUserId(String userId);
    List<ResumeDto> findAllInactiveResumesByUserId(String userId);
    ResumeDto findById(String id);
    long count();
    ResumeDto createResume(ResumeCreateDto resumeDto);
    ResumeDto updateResume(ResumeUpdateDto resumeDto);
}
