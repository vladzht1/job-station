package edu.rut_miit.job_station.repositories.base;

import java.util.List;
import java.util.Optional;

public interface ReadRepository<E, ID> {
    List<E> findAll();
    Optional<E> findById(ID id);
    long count();
}
