package com.spring.boot.task3springboot.mapper;

import com.spring.boot.task3springboot.dto.UserDto;
import com.spring.boot.task3springboot.model.User;
import com.spring.boot.task3springboot.vm.UserResponseVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE_USER = Mappers.getMapper(AppUserMapper.class);

    @Mapping(source = "posts", target = "posts")
    UserDto toUserDto(User user);

    UserResponseVm toUserResponseVm(User user);

    @Mapping(source = "posts", target = "posts")
    User toUser(UserDto userDto);

    @Mapping(target = "posts", ignore = true)
    User toUser(UserResponseVm userResponseVm);

}
