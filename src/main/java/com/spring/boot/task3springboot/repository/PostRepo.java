package com.spring.boot.task3springboot.repository;
import com.spring.boot.task3springboot.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    @Query("select p from Post p left join p.user u ")
    List<Post> getPostsByUsers();

    @Query("select p from Post p join p.user u where p.id=:id")
    Post getPostByIdWithUser(Long id);
}
