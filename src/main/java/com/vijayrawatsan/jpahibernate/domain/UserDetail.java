package com.vijayrawatsan.jpahibernate.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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

    @OneToOne(fetch = FetchType.LAZY) // Why you lie about being lazy?
    @JoinColumn(name = "user_id")
    private User user;

    public UserDetail() {
    }

    public UserDetail(Long id, String userPreference, String gender, User user) {
        this.id = id;
        this.userPreference = userPreference;
        this.gender = gender;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static interface IdStep {
        UserPreferenceStep withId(Long id);
    }

    public static interface UserPreferenceStep {
        GenderStep withUserPreference(String userPreference);
    }

    public static interface GenderStep {
        UserStep withGender(String gender);
    }

    public static interface UserStep {
        BuildStep withUser(User user);
    }

    public static interface BuildStep {
        UserDetail build();
    }

    public static class Builder implements IdStep, UserPreferenceStep, GenderStep, UserStep, BuildStep {
        private Long id;
        private String userPreference;
        private String gender;
        private User user;

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
        public UserStep withGender(String gender) {
            this.gender = gender;
            return this;
        }

        @Override
        public BuildStep withUser(User user) {
            this.user = user;
            return this;
        }

        @Override
        public UserDetail build() {
            return new UserDetail(
                this.id,
                this.userPreference,
                this.gender,
                this.user
            );
        }
    }
}
