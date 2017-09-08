package com.vijayrawatsan.jpahibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by vijayrawatsan on 07/09/17.
 */
@Entity
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userPreference;
    private String gender;

    public UserDetail() {
    }

    public UserDetail(Long id, String userPreference, String gender) {
        this.id = id;
        this.userPreference = userPreference;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserPreference() {
        return userPreference;
    }

    public void setUserPreference(String userPreference) {
        this.userPreference = userPreference;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static interface IdStep {
        UserPreferenceStep withId(Long id);
    }

    public static interface UserPreferenceStep {
        GenderStep withUserPreference(String userPreference);
    }

    public static interface GenderStep {
        BuildStep withGender(String gender);
    }

    public static interface BuildStep {
        UserDetail build();
    }

    public static class Builder implements IdStep, UserPreferenceStep, GenderStep, BuildStep {
        private Long id;
        private String userPreference;
        private String gender;

        private Builder() {
        }

        public static IdStep userDetail() {
            return new Builder();
        }

        @Override
        public UserPreferenceStep withId(Long id) {
            this.id = id;
            return this;
        }

        @Override
        public GenderStep withUserPreference(String userPreference) {
            this.userPreference = userPreference;
            return this;
        }

        @Override
        public BuildStep withGender(String gender) {
            this.gender = gender;
            return this;
        }

        @Override
        public UserDetail build() {
            return new UserDetail(
                this.id,
                this.userPreference,
                this.gender
            );
        }
    }
}
