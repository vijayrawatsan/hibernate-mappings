package com.vijayrawatsan.jpahibernate.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by vijayrawatsan on 11/09/17.
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Address() {
    }

    public Address(Long id, String address, User user) {
        this.id = id;
        this.address = address;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static interface IdStep {
        AddressStep withId(Long id);
    }

    public static interface AddressStep {
        UserStep withAddress(String address);
    }

    public static interface UserStep {
        BuildStep withUser(User user);
    }

    public static interface BuildStep {
        Address build();
    }

    public static class Builder implements IdStep, AddressStep, UserStep, BuildStep {
        private Long id;
        private String address;
        private User user;

        private Builder() {
        }

        public static IdStep address() {
            return new Builder();
        }

        @Override
        public AddressStep withId(Long id) {
            this.id = id;
            return this;
        }

        @Override
        public UserStep withAddress(String address) {
            this.address = address;
            return this;
        }

        @Override
        public BuildStep withUser(User user) {
            this.user = user;
            return this;
        }

        @Override
        public Address build() {
            return new Address(
                this.id,
                this.address,
                this.user
            );
        }
    }
}
