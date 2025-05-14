package com.spring.boot.task3springboot.service.service_impl;

import com.spring.boot.task3springboot.dto.UserDto;
import com.spring.boot.task3springboot.mapper.AppUserMapper;
import com.spring.boot.task3springboot.model.User;
import com.spring.boot.task3springboot.repository.UserRepo;
import com.spring.boot.task3springboot.service.UserService;
import com.spring.boot.task3springboot.vm.UserResponseVm;
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
    public UserResponseVm createUser(UserDto userDto) throws SystemException {
        if (Objects.nonNull(userDto.getId())) {
            throw new SystemException("error.id.must.be.null");
        }
        User user = AppUserMapper.INSTANCE_USER.toUser(userDto);
        user = userRepo.save(user);
        return AppUserMapper.INSTANCE_USER.toUserResponseVm(user);
    }

    @Override
    public UserResponseVm updateUser(UserDto userDto) throws SystemException {
        if (Objects.isNull(userDto.getId())) {
            throw new SystemException("error.id.must.be.notnull");
        }
        if (Objects.isNull(getUserById(userDto.getId()))) {
            throw new SystemException("error.user.notfound");
        }
        User user = AppUserMapper.INSTANCE_USER.toUser(userDto);
        user = userRepo.save(user);
        return AppUserMapper.INSTANCE_USER.toUserResponseVm(user);
    }

    @Override
    public UserResponseVm getUserById(Long id) throws SystemException {
        try {
            if (Objects.isNull(id)) {
                throw new SystemException("error.id.must.be.notnull");
            }
            Optional<User> user = userRepo.findById(id);
            if (user.isEmpty()) {
                throw new SystemException("error.user.notfound");
            }
            return AppUserMapper.INSTANCE_USER.toUserResponseVm(user.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SystemException("something.went.wrong");
        }
    }

    @Override
    public List<UserResponseVm> getUsers() {
        List<User> users = userRepo.findAll();
        return getUserResponseVms(users);
    }

    private static List<UserResponseVm> getUserResponseVms(List<User> users) {
        return users.stream().map(AppUserMapper.INSTANCE_USER::toUserResponseVm).collect(Collectors.toList());
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
    public UserResponseVm getUserWithAllPosts(Long id) throws SystemException {
        if (Objects.isNull(id)) {
            throw new SystemException("error.id.must.be.notnull");
        }
        User user = userRepo.getUserWithAllPosts(id);
        return AppUserMapper.INSTANCE_USER.toUserResponseVm(user);
    }
}
