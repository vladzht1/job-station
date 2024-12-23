package edu.rut_miit.job_station.boot;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.rut_miit.job_station.entities.Role;
import edu.rut_miit.job_station.entities.User;
import edu.rut_miit.job_station.entities.UserRoles;
import edu.rut_miit.job_station.repositories.RoleRepository;
import edu.rut_miit.job_station.repositories.UserRepository;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final String defaultPassword;

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public ConsoleRunner(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, @Value("${app.default.password}") String defaultPassword) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

        this.defaultPassword = defaultPassword;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(UserRoles.USER));
            roleRepository.save(new Role(UserRoles.ADMIN));
        }

        if (userRepository.count() == 0) {
            Role userRole = roleRepository.findByName(UserRoles.USER);
            Role adminRole = roleRepository.findByName(UserRoles.ADMIN);

            User admin = new User("Владислав", "Александрович", "Желтов", "admin", passwordEncoder.encode(defaultPassword), true);
            admin.setRoles(List.of(userRole, adminRole));

            userRepository.save(admin);
        }
    }
}
