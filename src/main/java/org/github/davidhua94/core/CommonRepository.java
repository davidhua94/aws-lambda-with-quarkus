package org.github.davidhua94.core;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * @author David Hua
 * @date 2021/4/9
 * @desc
 */
@ApplicationScoped
public class CommonRepository {

    @Inject
    EntityManager entityManager;

    public <T> T get(Serializable id, Class<T> clazz) {
        return entityManager.find(clazz, id);
    }

    @Transactional
    public <T> void save(T entity) {
        entityManager.persist(entity);
    }

//    @Transactional
//    public <T> void update(Serializable id, T entity) {
//        T exist = get(id, entity.getClass());
//    }
//
//    @Transactional
//    public <T> void delete(Serializable id, Class<T> entityClazz) {
//        T exist = get(id, entityClazz);
//        if (exist != null) {
//            entityManager.remove(exist);
//        }
//    }


}
