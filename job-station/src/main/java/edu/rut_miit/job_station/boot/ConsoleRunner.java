package edu.rut_miit.job_station.boot;

import org.springframework.stereotype.Component;

import edu.rut_miit.job_station.entities.User;
import edu.rut_miit.job_station.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@Component
public class ConsoleRunner implements CommandLineRunner  {
    private UserRepository userRepository;

    @Autowired
    public ConsoleRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(1 + 2 + "3");

        // userRepository.save(new User("Владислав", "Александрович", "Желтов", "vladzht1", "password"));
    }
}
