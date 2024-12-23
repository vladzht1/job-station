package edu.rut_miit.job_station.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.rut_miit.job_station.dto.user.UserCreateDto;
import edu.rut_miit.job_station.dto.user.UserUpdateDto;
import edu.rut_miit.job_station.dto.user.UserDto;
import edu.rut_miit.job_station.entities.Role;
import edu.rut_miit.job_station.entities.User;
import edu.rut_miit.job_station.entities.UserRoles;
import edu.rut_miit.job_station.exceptions.ClientException;
import edu.rut_miit.job_station.repositories.RoleRepository;
import edu.rut_miit.job_station.repositories.UserRepository;
import edu.rut_miit.job_station.services.UserService;
import edu.rut_miit.job_station.validation.ValidationUtil;
import jakarta.validation.ConstraintViolation;

@Service
public class UserServiceImpl implements UserService {
    private static final boolean DEFAULT_IS_COMMERCIAL = false;

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository
            .findAll()
            .stream()
            .map(u -> modelMapper.map(u, UserDto.class))
            .toList();
    }

    @Override
    public UserDto findUserById(String id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            throw new ClientException.NotFoundException("User was not found");
        }

        return modelMapper.map(userOpt.get(), UserDto.class);
    }

    @Override
    public UserDto findUserByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username), UserDto.class);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public UserDto addUser(UserCreateDto userDto) {
        if (!validationUtil.isValid(userDto)) {
            List<String> violations = validationUtil.violations(userDto)
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

            System.out.println(violations.get(0));
            return null;
        }

        User user = new User(
            userDto.getFirstName(),
            userDto.getMiddleName(),
            userDto.getLastName(),
            userDto.getLogin(),
            passwordEncoder.encode(userDto.getPassword()),
            DEFAULT_IS_COMMERCIAL
        );

        user.setRoles(getDefaultRoles());

        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto updateUser(UserUpdateDto userDto) {
        if (!validationUtil.isValid(userDto)) {
            List<String> violations = validationUtil.violations(userDto)
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

            System.out.println(violations.get(0));
            return null;
        }

        Optional<User> userOpt = userRepository.findById(userDto.getId());

        if (userOpt.isEmpty()) {
            throw new ClientException.NotFoundException("User was not found");
        }

        User user = userOpt.get();

        if (userDto.getFirstName() != null) user.setFirstName(userDto.getFirstName());
        if (userDto.getMiddleName() != null) user.setMiddleName(userDto.getMiddleName());
        if (userDto.getLastName() != null) user.setLastName(userDto.getLastName());
        if (userDto.getLogin() != null) user.setUsername(userDto.getLogin());
        if (userDto.getPassword() != null) user.setPassword(userDto.getPassword());
        if (userDto.isCommercialAccount() != user.isCommercialAccount()) user.setCommercialAccount(userDto.isCommercialAccount());

        return modelMapper.map(userRepository.update(user), UserDto.class);
    }

    @Override
    public UserDto block(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ClientException.NotFoundException("User was not found"));
        user.block();

        return modelMapper.map(userRepository.update(user), UserDto.class);
    }

    @Override
    public UserDto unblock(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ClientException.NotFoundException("User was not found"));
        user.unblock();

        return modelMapper.map(userRepository.update(user), UserDto.class);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setValidationUtil(ValidationUtil validationUtils) {
        this.validationUtil = validationUtils;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private List<Role> getDefaultRoles() {
        Role userRole = roleRepository.findByName(UserRoles.USER);
        return List.of(userRole);
    }
}
