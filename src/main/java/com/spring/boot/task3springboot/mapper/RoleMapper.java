package com.spring.boot.task3springboot.mapper;

import com.spring.boot.task3springboot.dto.RoleDto;
import com.spring.boot.task3springboot.model.Role;
import com.spring.boot.task3springboot.vm.RoleDtoVm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper ROLE_MAPPER = Mappers.getMapper(RoleMapper.class);

    RoleDtoVm toRoleDtoVm(Role role);

    Role toRole(RoleDto roleDto);

    RoleDto toRoleDto(Role role);
}
