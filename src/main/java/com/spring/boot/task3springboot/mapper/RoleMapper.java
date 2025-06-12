package com.spring.boot.task3springboot.mapper;

import com.spring.boot.task3springboot.dto.RoleDto;
import com.spring.boot.task3springboot.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper ROLE_MAPPER = Mappers.getMapper(RoleMapper.class);

    Role toRole(RoleDto roleDto);

    RoleDto toRoleDto(Role role);
}
