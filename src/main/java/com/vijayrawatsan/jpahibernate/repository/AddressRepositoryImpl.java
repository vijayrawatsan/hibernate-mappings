package com.vijayrawatsan.jpahibernate.repository;

import com.vijayrawatsan.jpahibernate.domain.Address;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by vijayrawatsan on 14/09/17.
 */
public class AddressRepositoryImpl implements AddressRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override public Address findFirstAddressByUserId(Long id) {
        String query = "select a from Address a where a.user.id = :id";

        List<Address> resultList = entityManager
            .createQuery(query, Address.class)
            .setParameter("id", id)
            .setMaxResults(1)
            .getResultList();

        return resultList.get(0);
    }

    @Override public void deleteFirstAddressByUserId(Long id) {
        String query = "delete from Address where user_id = :id limit 1";

        entityManager
            .createNativeQuery(query)
            .setParameter("id", id)
            .executeUpdate();
    }
}
