package com.spring.boot.task3springboot.repository;

import com.spring.boot.task3springboot.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository<Account, Long> {
}
