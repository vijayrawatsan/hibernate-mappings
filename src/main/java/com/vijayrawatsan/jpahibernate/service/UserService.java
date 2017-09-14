package com.vijayrawatsan.jpahibernate.service;

import com.google.common.collect.Lists;
import com.vijayrawatsan.jpahibernate.domain.Address;
import com.vijayrawatsan.jpahibernate.domain.User;
import com.vijayrawatsan.jpahibernate.repository.AddressRepository;
import com.vijayrawatsan.jpahibernate.repository.UserRepository;
import java.util.ArrayList;
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

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository userDetailRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class)
    public User createUser() {
        User user = getUser();
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public User findUser(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public String findFirstAddress(Long id) {
        User one = userRepository.findOne(id);
        return one.getAddresses().get(0).getAddress();
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteFirstAddress(Long id) {
        User one = userRepository.findOne(id);
        one.getAddresses().remove(0);
    }

    private User getUser() {
        User user = User.Builder.user().withId(null).withUserName("a").withAddresses(null).build();
        ArrayList<Address> addresses = Lists.newArrayList(Address.Builder.address()
            .withId(null)
            .withAddress("Add1")
            .withUser(user)
            .build(), Address.Builder.address()
            .withId(null)
            .withAddress("Add2")
            .withUser(user)
            .build());
        user.setAddresses(addresses);
        return user;
    }
}
