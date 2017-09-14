package com.vijayrawatsan.jpahibernate.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_address",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses;

    public User() {
    }

    public User(Long id, String userName, List<Address> addresses) {
        this.id = id;
        this.userName = userName;
        this.addresses = addresses;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public static interface IdStep {
        UserNameStep withId(Long id);
    }

    public static interface UserNameStep {
        AddressesStep withUserName(String userName);
    }

    public static interface AddressesStep {
        BuildStep withAddresses(List<Address> addresses);
    }

    public static interface BuildStep {
        User build();
    }

    public static class Builder implements IdStep, UserNameStep, AddressesStep, BuildStep {
        private Long id;
        private String userName;
        private List<Address> addresses;

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
        public AddressesStep withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        @Override
        public BuildStep withAddresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        @Override
        public User build() {
            return new User(
                this.id,
                this.userName,
                this.addresses
            );
        }
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userName != null ? userName.equals(user.userName) : user.userName == null;
    }

    @Override public int hashCode() {
        return userName != null ? userName.hashCode() : 0;
    }
}
