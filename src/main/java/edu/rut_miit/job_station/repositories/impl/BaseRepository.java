package edu.rut_miit.job_station.repositories.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.repositories.base.CreateRepository;
import edu.rut_miit.job_station.repositories.base.DeleteRepository;
import edu.rut_miit.job_station.repositories.base.ReadRepository;
import edu.rut_miit.job_station.repositories.base.UpdateRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public abstract class BaseRepository<E, ID> implements
    ReadRepository<E, ID>,
    CreateRepository<E, ID>,
    UpdateRepository<E, ID>,
    DeleteRepository<E, ID> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<E> entityClass;

    public BaseRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Iterable<E> findAll() {
        return entityManager().createQuery(entityClass.getName(), entityClass).getResultList();
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.ofNullable(entityManager().find(entityClass, id));
    }

    @Transactional
    @Override
    public E save(E entity) {
        entityManager().persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public E update(E entity) {
        return entityManager().merge(entity);
    }

    @Transactional
    @Override
    public void delete(E entity) {
        entityManager().remove(entity);
    }

    @Override
    public void deleteById(ID id) {
        Optional<E> entity = findById(id);

        if (entity.isEmpty()) {
            return;
        }

        delete(entity.get());
    }

    protected EntityManager entityManager() {
        return entityManager;
    }
}
