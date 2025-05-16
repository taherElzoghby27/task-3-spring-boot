package com.spring.boot.task3springboot.service;

import com.spring.boot.task3springboot.vm.RoleDtoVm;

public interface RoleService {
    RoleDtoVm findByRole(String role);
}
