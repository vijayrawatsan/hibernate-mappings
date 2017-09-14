package com.vijayrawatsan.jpahibernate.repository;

import com.vijayrawatsan.jpahibernate.domain.Address;

/**
 * Created by vijayrawatsan on 14/09/17.
 */
public interface AddressRepositoryCustom {

    Address findFirstAddressByUserId(Long id);

    void deleteFirstAddressByUserId(Long id);
}
