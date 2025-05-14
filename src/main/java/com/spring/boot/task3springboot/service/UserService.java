package com.spring.boot.task3springboot.service;

import com.spring.boot.task3springboot.dto.UserDto;
import com.spring.boot.task3springboot.vm.UserResponseVm;
import jakarta.transaction.SystemException;

import java.util.List;

public interface UserService {
    UserResponseVm createUser(UserDto user) throws SystemException;

    UserResponseVm updateUser(UserDto user) throws SystemException;

    UserResponseVm getUserById(Long id) throws SystemException;

    List<UserResponseVm> getUsers();

    void deleteUserById(Long id) throws SystemException;

    UserResponseVm getUserWithAllPosts(Long id) throws SystemException;
}
