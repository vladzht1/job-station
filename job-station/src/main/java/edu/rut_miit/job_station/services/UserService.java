package edu.rut_miit.job_station.services;

import java.util.Optional;

import edu.rut_miit.job_station.dto.user.AddUserDto;
import edu.rut_miit.job_station.dto.user.UpdateUserDto;
import edu.rut_miit.job_station.entities.User;

public interface UserService {
    Iterable<User> findAllUsers();
    Optional<User> findUserById(String id);
    User addUser(AddUserDto userDto);
    User updateUser(UpdateUserDto userDto);
}
