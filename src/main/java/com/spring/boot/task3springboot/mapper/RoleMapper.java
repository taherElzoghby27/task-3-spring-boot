package com.spring.boot.task3springboot.mapper;

import com.spring.boot.task3springboot.model.Role;
import com.spring.boot.task3springboot.vm.RoleDtoVm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper ROLE_MAPPER = Mappers.getMapper(RoleMapper.class);

    RoleDtoVm toRoleDtoVm(Role role);
}
