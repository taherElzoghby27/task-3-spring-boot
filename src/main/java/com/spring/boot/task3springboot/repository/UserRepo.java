package com.spring.boot.task3springboot.repository;
import com.spring.boot.task3springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select u from User u left join Post p where u.id=:id")
    User getUserWithAllPosts(Long id);

    @Query("select u from User u left join Post p")
    List<User> getUsersWithAllPosts();
}
