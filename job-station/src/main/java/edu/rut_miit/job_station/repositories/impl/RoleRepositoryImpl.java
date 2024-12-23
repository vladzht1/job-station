package edu.rut_miit.job_station.repositories.impl;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.entities.Role;
import edu.rut_miit.job_station.entities.UserRoles;
import edu.rut_miit.job_station.repositories.RoleRepository;

@Repository
public class RoleRepositoryImpl extends BaseRepository<Role, String> implements RoleRepository {
    public RoleRepositoryImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(UserRoles role) {
        return entityManager()
            .createQuery("select r from Role r where r.name = :role", Role.class)
            .setParameter("role", role)
            .getSingleResult();
    }
}
