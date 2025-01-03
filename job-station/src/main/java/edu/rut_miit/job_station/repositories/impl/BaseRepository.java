package edu.rut_miit.job_station.repositories.impl;

import java.util.List;
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
    public List<E> findAll() {
        // if (pageable == null) {
        //     pageable = Pageable.createDefault();
        // }

        // String query = "select t from " + entityClass.getSimpleName()
        //     + " t order by t.id asc limit " + pageable.getLimit()
        //     + " offset " + ((pageable.getPage() - 1) * pageable.getLimit());

        // return entityManager().createQuery(query, entityClass).getResultList();

        return entityManager().createQuery("select t from " + entityClass.getSimpleName() + " t order by t.id asc", entityClass).getResultList();
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.ofNullable(entityManager().find(entityClass, id));
    }

    @Override
    public long count() {
        var result = entityManager().createQuery(
            "select count(*) amount from " + entityClass.getSimpleName(),
            Long.class
        ).getSingleResult();

        return result;
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
