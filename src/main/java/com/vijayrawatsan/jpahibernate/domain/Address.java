package com.vijayrawatsan.jpahibernate.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Created by vijayrawatsan on 11/09/17.
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;

    @ManyToMany(mappedBy = "addresses")
    private List<User> users;

    public Address() {
    }

    public Address(Long id, String address, List<User> users) {
        this.id = id;
        this.address = address;
        this.users = users;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static interface IdStep {
        AddressStep withId(Long id);
    }

    public static interface AddressStep {
        UsersStep withAddress(String address);
    }

    public static interface UsersStep {
        BuildStep withUsers(List<User> users);
    }

    public static interface BuildStep {
        Address build();
    }

    public static class Builder implements IdStep, AddressStep, UsersStep, BuildStep {
        private Long id;
        private String address;
        private List<User> users;

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
        public UsersStep withAddress(String address) {
            this.address = address;
            return this;
        }

        @Override
        public BuildStep withUsers(List<User> users) {
            this.users = users;
            return this;
        }

        @Override
        public Address build() {
            return new Address(
                this.id,
                this.address,
                this.users
            );
        }
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address1 = (Address) o;

        return address != null ? address.equals(address1.address) : address1.address == null;
    }

    @Override public int hashCode() {
        return address != null ? address.hashCode() : 0;
    }
}
