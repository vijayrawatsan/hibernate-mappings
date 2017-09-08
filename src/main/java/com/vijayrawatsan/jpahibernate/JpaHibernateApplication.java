package com.vijayrawatsan.jpahibernate;

import com.vijayrawatsan.jpahibernate.domain.User;
import com.vijayrawatsan.jpahibernate.service.UserService;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class JpaHibernateApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(JpaHibernateApplication.class);
    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) throws Exception {
        Runtime.getRuntime().exec("sh /mnt/dropjpa.sh").waitFor();
        SpringApplication.run(JpaHibernateApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        entityManager.createNativeQuery("select '---------------------------------------------------------------------' ").getResultList();
        User user = userService.createUser();
        userService.findUser(user.getId());
        entityManager.createNativeQuery("select '---------------------------------------------------------------------' ").getResultList();
    }

}
