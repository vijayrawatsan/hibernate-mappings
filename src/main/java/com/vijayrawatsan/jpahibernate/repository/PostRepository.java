package com.vijayrawatsan.jpahibernate.repository;

import com.vijayrawatsan.jpahibernate.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by vijayrawatsan on 14/09/17.
 */
public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

    @Query("select p from Post p where p.content = ?1")
    Post findPost(String content);

    @Query(value = "select * from Post where content = ?1", nativeQuery = true)
    Post findPost2(String content);

    //@Modifying(clearAutomatically = true)
    //@Query(value = "update Post set content = 'a'", nativeQuery = true)
    //void updatePost();
}
