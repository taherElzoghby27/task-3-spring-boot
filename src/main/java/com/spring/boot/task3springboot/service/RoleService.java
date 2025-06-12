package com.spring.boot.task3springboot.service;

import com.spring.boot.task3springboot.dto.RoleDto;
import com.spring.boot.task3springboot.model.Role;

import java.util.List;

public interface RoleService {
    RoleDto findByRole(String role);

    List<RoleDto> update(List<Role> roles);
}
