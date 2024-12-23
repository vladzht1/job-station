package edu.rut_miit.job_station.services;

import java.util.List;

import edu.rut_miit.job_station.dto.user.UserCreateDto;
import edu.rut_miit.job_station.dto.user.UserUpdateDto;
import edu.rut_miit.job_station.dto.user.UserDto;

public interface UserService {
    List<UserDto> findAllUsers();
    UserDto findUserById(String id);
    UserDto findUserByUsername(String username);
    long count();
    UserDto addUser(UserCreateDto userDto);
    UserDto updateUser(UserUpdateDto userDto);
    UserDto block(String id);
    UserDto unblock(String id);
}
