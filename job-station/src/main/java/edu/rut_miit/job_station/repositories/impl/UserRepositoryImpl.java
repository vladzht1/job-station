package edu.rut_miit.job_station.repositories.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.entities.User;
import edu.rut_miit.job_station.repositories.UserRepository;

@Repository
public class UserRepositoryImpl extends BaseRepository<User, String> implements UserRepository {
    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return Optional.ofNullable(
                entityManager()
                    .createQuery("select u from User u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult()
            );
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return Optional.empty();
        }
    }
}
