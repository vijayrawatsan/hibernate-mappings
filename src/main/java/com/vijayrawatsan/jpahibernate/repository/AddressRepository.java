package com.vijayrawatsan.jpahibernate.repository;

import com.vijayrawatsan.jpahibernate.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vijayrawatsan on 07/09/17.
 */
public interface AddressRepository extends JpaRepository<Address, Long>, AddressRepositoryCustom {
}