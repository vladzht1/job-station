package edu.rut_miit.job_station.repositories;

import java.util.Optional;

import edu.rut_miit.job_station.entities.User;
import edu.rut_miit.job_station.repositories.base.CreateRepository;
import edu.rut_miit.job_station.repositories.base.ReadRepository;
import edu.rut_miit.job_station.repositories.base.UpdateRepository;

public interface UserRepository extends
    ReadRepository<User, String>,
    CreateRepository<User, String>,
    UpdateRepository<User, String> {

    Optional<User> findByUsername(String username);
}
