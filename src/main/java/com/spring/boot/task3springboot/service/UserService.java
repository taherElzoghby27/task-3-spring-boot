package com.spring.boot.task3springboot.service;

import com.spring.boot.task3springboot.dto.UserDto;
import jakarta.transaction.SystemException;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user) throws SystemException;

    UserDto updateUser(UserDto user) throws SystemException;

    UserDto getUserById(Long id) throws SystemException;

    List<UserDto> getUsers();

    void deleteUserById(Long id) throws SystemException;

    UserDto getUserWithAllPosts(Long id) throws SystemException;

    List<UserDto> getUsersWithAllPosts();
}
