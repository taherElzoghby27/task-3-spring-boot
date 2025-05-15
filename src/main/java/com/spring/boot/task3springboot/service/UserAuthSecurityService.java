package com.spring.boot.task3springboot.service;

import com.spring.boot.task3springboot.dto.UserSecurityDto;

import java.util.List;

public interface UserAuthSecurityService {
    List<UserSecurityDto> getAllUsers();

    UserSecurityDto getUserByUserName(String username);
}
