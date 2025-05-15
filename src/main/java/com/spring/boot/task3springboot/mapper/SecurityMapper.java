package com.spring.boot.task3springboot.mapper;

import com.spring.boot.task3springboot.dto.UserSecurityDto;
import com.spring.boot.task3springboot.model.security.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SecurityMapper {
    SecurityMapper INSTANCE_SECURITY_MAPPER = Mappers.getMapper(SecurityMapper.class);

    UserSecurityDto toUserSecurityDto(Users user);
}
