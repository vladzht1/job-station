package edu.rut_miit.job_station.repositories.base;

import java.util.Optional;

public interface ReadRepository<E, ID> {
    Iterable<E> findAll();
    Optional<E> findById(ID id);
}
