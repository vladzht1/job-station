package edu.rut_miit.job_station.repositories;

import edu.rut_miit.job_station.entities.Role;
import edu.rut_miit.job_station.entities.UserRoles;
import edu.rut_miit.job_station.repositories.base.CreateRepository;
import edu.rut_miit.job_station.repositories.base.ReadRepository;

public interface RoleRepository extends
    ReadRepository<Role, String>,
    CreateRepository<Role, String> {

    Role findByName(UserRoles role);
}
