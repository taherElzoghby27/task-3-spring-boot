package com.spring.boot.task3springboot.service.service_impl;

import com.spring.boot.task3springboot.dto.UserSecurityDto;
import com.spring.boot.task3springboot.mapper.SecurityMapper;
import com.spring.boot.task3springboot.model.security.Users;
import com.spring.boot.task3springboot.repository.UserSecurityRepo;
import com.spring.boot.task3springboot.service.UserAuthSecurityService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAuthSecurityServiceImpl implements UserAuthSecurityService {
    @Autowired
    private UserSecurityRepo userSecurityRepo;

    @Override
    public List<UserSecurityDto> getAllUsers() {
        List<Users> users = userSecurityRepo.findAll();
        return users.stream().map(SecurityMapper.INSTANCE_SECURITY_MAPPER::toUserSecurityDto).toList();
    }

    @Override
    public UserSecurityDto getUserByUserName(String username) {
        try {
            Optional<Users> user = userSecurityRepo.findByUsername(username);
            if (user.isEmpty()) {
                throw new SystemException("error.user.notfound");
            }
            return SecurityMapper.INSTANCE_SECURITY_MAPPER.toUserSecurityDto(user.get());
        } catch (SystemException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
