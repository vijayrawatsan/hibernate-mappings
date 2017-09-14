package com.vijayrawatsan.jpahibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by vijayrawatsan on 11/09/17.
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;

    public Address() {
    }

    public Address(Long id, String address) {
        this.id = id;
        this.address = address;
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

    public static interface IdStep {
        AddressStep withId(Long id);
    }

    public static interface AddressStep {
        BuildStep withAddress(String address);
    }

    public static interface BuildStep {
        Address build();
    }

    public static class Builder implements IdStep, AddressStep, BuildStep {
        private Long id;
        private String address;

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
        public BuildStep withAddress(String address) {
            this.address = address;
            return this;
        }

        @Override
        public Address build() {
            return new Address(
                this.id,
                this.address
            );
        }
    }
}
