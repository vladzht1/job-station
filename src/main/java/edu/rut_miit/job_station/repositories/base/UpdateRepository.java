package edu.rut_miit.job_station.repositories.base;

public interface UpdateRepository<E, ID> {
    E update(E entity);
}
