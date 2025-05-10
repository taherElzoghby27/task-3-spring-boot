package com.spring.boot.task3springboot.service.service_impl;

import com.spring.boot.task3springboot.dto.UserDto;
import com.spring.boot.task3springboot.mapper.AppUserMapper;
import com.spring.boot.task3springboot.model.User;
import com.spring.boot.task3springboot.repository.UserRepo;
import com.spring.boot.task3springboot.service.UserService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) throws SystemException {
        if (Objects.nonNull(userDto.getId())) {
            throw new SystemException("error.id.must.be.null");
        }
        User user = AppUserMapper.INSTANCE.toUser(userDto);
        user = userRepo.save(user);
        return AppUserMapper.INSTANCE.toUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) throws SystemException {
        if (Objects.isNull(userDto.getId())) {
            throw new SystemException("error.id.must.be.notnull");
        }
        if (Objects.isNull(getUserById(userDto.getId()))) {
            throw new SystemException("error.user.notfound");
        }
        User user = AppUserMapper.INSTANCE.toUser(userDto);
        user = userRepo.save(user);
        return AppUserMapper.INSTANCE.toUserDto(user);
    }

    @Override
    public UserDto getUserById(Long id) throws SystemException {
        if (Objects.isNull(id)) {
            throw new SystemException("error.id.must.be.notnull");
        }
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new SystemException("error.user.notfound");
        }
        return AppUserMapper.INSTANCE.toUserDto(user.get());
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepo.findAll();
        return getUsersDto(users);
    }

    private static List<UserDto> getUsersDto(List<User> users) {
        return users.stream().map(AppUserMapper.INSTANCE::toUserDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) throws SystemException {
        if (Objects.isNull(id)) {
            throw new SystemException("error.id.must.be.notnull");
        }
        if (Objects.isNull(getUserById(id))) {
            throw new SystemException("error.user.notfound");
        }
        userRepo.deleteById(id);
    }

    @Override
    public UserDto getUserWithAllPosts(Long id) throws SystemException {
        if (Objects.isNull(id)) {
            throw new SystemException("error.id.must.be.notnull");
        }
        User user = userRepo.getUserWithAllPosts(id);
        return AppUserMapper.INSTANCE.toUserDto(user);
    }

    @Override
    public List<UserDto> getUsersWithAllPosts() {
        List<User> users = userRepo.getUsersWithAllPosts();
        return getUsersDto(users);
    }
}
