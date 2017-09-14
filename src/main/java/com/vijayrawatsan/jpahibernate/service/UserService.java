package com.vijayrawatsan.jpahibernate.service;

import com.google.common.collect.Lists;
import com.vijayrawatsan.jpahibernate.domain.Address;
import com.vijayrawatsan.jpahibernate.domain.User;
import com.vijayrawatsan.jpahibernate.repository.AddressRepository;
import com.vijayrawatsan.jpahibernate.repository.UserRepository;
import java.util.List;
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
    private AddressRepository addressRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class)
    public User createUser(String userName) {
        User user1 = getUser(userName);
        Address address1 = addressRepository.findByAddress("a1");
        Address address2 = addressRepository.findByAddress("a2");
        if (address1 == null) {
            address1 = getAddress("a1");
        }
        if (address2 == null) {
            address2 = getAddress("a2");
        }
        user1.setAddresses(Lists.newArrayList(address1, address2));
        address1.setUsers(Lists.newArrayList(user1));
        address2.setUsers(Lists.newArrayList(user1));

        return userRepository.save(user1);
    }

    //@Transactional(rollbackFor = Exception.class)
    //public List<Address> createAddresses(Long id) {
    //    User user = userRepository.findOne(id);
    //    ArrayList<Address> addresses = Lists.newArrayList(Address.Builder.address()
    //        .withId(null)
    //        .withAddress("Add1")
    //        .withUser(user)
    //        .build(), Address.Builder.address()
    //        .withId(null)
    //        .withAddress("Add2")
    //        .withUser(user)
    //        .build());
    //    List<Address> save =
    //        addressRepository.save(addresses);
    //    return save;
    //}

    @Transactional(rollbackFor = Exception.class)
    public User findUser(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public String findFirstAddress(Long id) {
        return addressRepository.findFirstAddressByUserId(id).getAddress();
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAddress(Long id, String addressVal) {
        User one = userRepository.findOne(id);
        Address address = addressRepository.findByAddress(addressVal);
        List<Address> addresses = one.getAddresses();
        addresses.remove(address);
        address.getUsers().remove(one);
        userRepository.save(one);
    }

    private User getUser(String userName) {
        User user = User.Builder.user().withId(null).withUserName(userName).withAddresses(null).build();
        return user;
    }

    private Address getAddress(String address) {
        return Address.Builder.address()
            .withId(null)
            .withAddress(address)
            .withUsers(null)
            .build();
    }
}
