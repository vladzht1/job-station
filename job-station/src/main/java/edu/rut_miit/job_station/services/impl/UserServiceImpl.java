package edu.rut_miit.job_station.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import edu.rut_miit.job_station.dto.user.AddUserDto;
import edu.rut_miit.job_station.dto.user.UpdateUserDto;
import edu.rut_miit.job_station.entities.User;
import edu.rut_miit.job_station.repositories.UserRepository;
import edu.rut_miit.job_station.services.UserService;
import edu.rut_miit.job_station.validation.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ValidationUtil validationUtil;

    @Override
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User addUser(AddUserDto userDto) {
        if (!validationUtil.isValid(userDto)) {
            List<String> violations = validationUtil.violations(userDto)
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

            throw new ValidationException(violations.get(0));
        }

        User user = new User(
            userDto.getFirstName(),
            userDto.getMiddleName(),
            userDto.getLastName(),
            userDto.getLogin(),
            userDto.getPassword()
        );

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UpdateUserDto userDto) {

    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setValidationUtil(ValidationUtil validationUtils) {
        this.validationUtil = validationUtils;
    }
}
