package com.spring.boot.task3springboot.mapper;

import com.spring.boot.task3springboot.dto.PostDto;
import com.spring.boot.task3springboot.model.Post;
import com.spring.boot.task3springboot.model.User;
import com.spring.boot.task3springboot.vm.PostVmRequest;
import com.spring.boot.task3springboot.vm.PostVmResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE_POST = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "user", target = "user")
    PostDto toPostDto(Post post);
    @Mapping(source = "user", target = "user")
    PostVmResponse toPostVmResponse(Post post);

    @Mapping(target = "user", ignore = true)
    Post toPost(PostVmResponse postVmResponse);

    @Mapping(source = "user", target = "user")
    Post toPost(PostDto postDto);

    @Mapping(source = "userId", target = "user")
    Post toPost(PostVmRequest postVmRequest);

    default User map(Long userId) {
        if (userId == null) return null;
        User user = new User();
        user.setId(userId);
        return user;
    }
}
