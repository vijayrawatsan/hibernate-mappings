package com.vijayrawatsan.jpahibernate.repository;

import com.vijayrawatsan.jpahibernate.domain.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vijayrawatsan on 07/09/17.
 */
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}
