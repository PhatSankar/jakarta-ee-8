package org.eclipse.jakarta.hello.base.dao;

import lombok.RequiredArgsConstructor;
import org.eclipse.jakarta.hello.base.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseDAO <T extends BaseEntity>{

    @PersistenceContext
    protected EntityManager em;

    private final Class<T> entityClass;

    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<T> cr = cb.createQuery(entityClass);

        Root<T> root = cr.from(entityClass);

        return em.createQuery(cr).getResultList();

    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(em.find(entityClass, id));
    }

    public T add(T entity) {
        em.persist(entity);
        return entity;
    }

    public void removeById(Long id) {
        Optional<T> result = findById(id);

        if(result.isPresent()) {
            em.remove(result.get());
        }
    }

    public T update(T entity) {
        em.merge(entity);
        return entity;

    }
}
