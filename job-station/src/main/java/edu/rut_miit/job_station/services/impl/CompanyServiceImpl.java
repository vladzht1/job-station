package edu.rut_miit.job_station.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.rut_miit.job_station.dto.company.CompanyCreateDto;
import edu.rut_miit.job_station.dto.company.CompanyDto;
import edu.rut_miit.job_station.dto.company.CompanyRatingDto;
import edu.rut_miit.job_station.dto.company.CompanyUpdateDto;
import edu.rut_miit.job_station.entities.Company;
import edu.rut_miit.job_station.entities.User;
import edu.rut_miit.job_station.exceptions.ClientException;
import edu.rut_miit.job_station.repositories.CompanyRepository;
import edu.rut_miit.job_station.repositories.UserRepository;
import edu.rut_miit.job_station.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public List<CompanyDto> findAllCompanies() {
        return companyRepository
            .findAll()
            .stream()
            .map(c -> modelMapper.map(c, CompanyDto.class))
            .toList();
    }

    @Override
    public List<CompanyDto> findCompaniesByCreatorId(String creatorId) {
        return companyRepository
            .findByCreatorId(creatorId)
            .stream()
            .map(c -> modelMapper.map(c, CompanyDto.class))
            .toList();
    }

    @Override
    public List<CompanyRatingDto> findMostRankedCompanies() {
        return companyRepository.findSortedByApplicationsAmountDesc(10);
    }

    @Override
    public CompanyDto findCompanyById(String id) {
        Optional<Company> companyOpt = companyRepository.findById(id);

        if (companyOpt.isEmpty()) {
            throw new ClientException.NotFoundException("Company was not found");
        }

        return modelMapper.map(companyOpt.get(), CompanyDto.class);
    }

    @Override
    public long count() {
        return companyRepository.count();
    }

    @Override
    public CompanyDto registerCompany(CompanyCreateDto companyDto, String creatorId) {
        if (companyRepository.findByName(companyDto.getName()).isPresent()) {
            throw new ClientException.ConflictException("Company with such name already exists");
        }

        User creator = userRepository.findById(creatorId).orElseThrow(() -> new ClientException.NotFoundException("User was not found"));
        Company company = new Company(companyDto.getName(), companyDto.getDescription(), creator);

        return modelMapper.map(companyRepository.save(company), CompanyDto.class);
    }

    @Override
    public CompanyDto updateCompany(String id, CompanyUpdateDto companyDto) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ClientException.NotFoundException("Company was not found"));

        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());

        return modelMapper.map(companyRepository.update(company), CompanyDto.class);
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
