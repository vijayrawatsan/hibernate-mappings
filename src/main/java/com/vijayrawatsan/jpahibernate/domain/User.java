package com.vijayrawatsan.jpahibernate.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by vijayrawatsan on 30/07/17.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserDetail userDetail;

    public User() {
    }

    public User(Long id, String userName, UserDetail userDetail) {
        this.id = id;
        this.userName = userName;
        this.userDetail = userDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public static interface IdStep {
        UserNameStep withId(Long id);
    }

    public static interface UserNameStep {
        UserDetailStep withUserName(String userName);
    }

    public static interface UserDetailStep {
        BuildStep withUserDetail(UserDetail userDetail);
    }

    public static interface BuildStep {
        User build();
    }

    public static class Builder implements IdStep, UserNameStep, UserDetailStep, BuildStep {
        private Long id;
        private String userName;
        private UserDetail userDetail;

        private Builder() {
        }

        public static IdStep user() {
            return new Builder();
        }

        @Override
        public UserNameStep withId(Long id) {
            this.id = id;
            return this;
        }

        @Override
        public UserDetailStep withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        @Override
        public BuildStep withUserDetail(UserDetail userDetail) {
            this.userDetail = userDetail;
            return this;
        }

        @Override
        public User build() {
            return new User(
                this.id,
                this.userName,
                this.userDetail
            );
        }
    }
}
