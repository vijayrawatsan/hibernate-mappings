package com.vijayrawatsan.jpahibernate.service;

import com.vijayrawatsan.jpahibernate.domain.User;
import com.vijayrawatsan.jpahibernate.domain.UserDetail;
import com.vijayrawatsan.jpahibernate.repository.UserDetailRepository;
import com.vijayrawatsan.jpahibernate.repository.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vijayrawatsan on 01/08/17.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @PersistenceContext
    private EntityManager entityManager;

    public User createUser() {
        User user = getUser();
        user.setUserDetail(getUserDetail());
        return userRepository.save(user);
    }

    public User findUser(Long id) {
        return userRepository.findOne(id);
    }

    private User getUser() {
        return User.Builder.user().withId(null).withUserName("a").withUserDetail(null).build();
    }

    private UserDetail getUserDetail() {
        return UserDetail.Builder
            .userDetail().withId(null).withUserPreference("blah").withGender("M").build();
    }
}
