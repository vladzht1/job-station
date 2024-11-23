package edu.rut_miit.job_station.repositories.base;

public interface DeleteRepository<E, ID> {
    void delete(E entity);
    void deleteById(ID id);
}
