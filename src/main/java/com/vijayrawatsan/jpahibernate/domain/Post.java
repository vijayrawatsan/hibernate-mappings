package com.vijayrawatsan.jpahibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by vijayrawatsan on 14/09/17.
 */
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    public Post() {
    }

    public Post(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static interface IdStep {
        ContentStep withId(Long id);
    }

    public static interface ContentStep {
        BuildStep withContent(String content);
    }

    public static interface BuildStep {
        Post build();
    }

    public static class Builder implements IdStep, ContentStep, BuildStep {
        private Long id;
        private String content;

        private Builder() {
        }

        public static IdStep post() {
            return new Builder();
        }

        @Override
        public ContentStep withId(Long id) {
            this.id = id;
            return this;
        }

        @Override
        public BuildStep withContent(String content) {
            this.content = content;
            return this;
        }

        @Override
        public Post build() {
            return new Post(
                this.id,
                this.content
            );
        }
    }
}
