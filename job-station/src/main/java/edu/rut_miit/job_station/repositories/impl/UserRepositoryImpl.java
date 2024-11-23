package edu.rut_miit.job_station.repositories.impl;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.entities.User;
import edu.rut_miit.job_station.repositories.UserRepository;

@Repository
public class UserRepositoryImpl extends BaseRepository<User, String> implements UserRepository {
    public UserRepositoryImpl() {
        super(User.class);
    }
}
