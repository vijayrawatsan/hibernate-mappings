package com.vijayrawatsan.jpahibernate.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 * Created by vijayrawatsan on 14/09/17.
 */
public class PostRepositoryImpl implements PostCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void updatePost() {
        String hsql = "update Post set content = 'a'";
        Query query = entityManager.createQuery(hsql);
        entityManager.flush();
        query.executeUpdate();
        entityManager.clear();
    }
}
