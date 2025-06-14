package com.spring.boot.task3springboot.service.service_impl;

import com.spring.boot.task3springboot.dto.RoleDto;
import com.spring.boot.task3springboot.enums.RoleEnum;
import com.spring.boot.task3springboot.mapper.RoleMapper;
import com.spring.boot.task3springboot.model.Role;
import com.spring.boot.task3springboot.repository.RoleRepo;
import com.spring.boot.task3springboot.service.RoleService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public RoleDto findByRole(String role) {
        try {
            Optional<Role> roleResult = roleRepo.findByRole(role);
            if (roleResult.isEmpty()) {
                throw new SystemException("error.role.not.found");
            }
            roleResult.get().setAccounts(null);
            return RoleMapper.ROLE_MAPPER.toRoleDto(roleResult.get());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<RoleDto> update(List<Role> roles) {
        try {
            List<Role> rolesResult = roleRepo.saveAll(roles);
            if (rolesResult.isEmpty()) {
                throw new SystemException("error.role.not.found");
            }
            return roles.stream().map(RoleMapper.ROLE_MAPPER::toRoleDto).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
