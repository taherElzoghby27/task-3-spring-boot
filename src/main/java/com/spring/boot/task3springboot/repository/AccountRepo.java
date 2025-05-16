package com.spring.boot.task3springboot.repository;

import com.spring.boot.task3springboot.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
