package edu.rut_miit.job_station.repositories.base;

public interface CreateRepository<E, ID> {
    E save(E entity);
}
