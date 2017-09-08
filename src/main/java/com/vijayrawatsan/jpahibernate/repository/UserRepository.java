package com.vijayrawatsan.jpahibernate.repository;

import com.vijayrawatsan.jpahibernate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by vijayrawatsan on 01/08/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select count(*) from User limit 0,50", nativeQuery = true)
    int nativeCount();

    @Query(value = "select count(*) from User")
    int nonNativeCount();
}
