package com.spring.boot.task3springboot.mapper;

import com.spring.boot.task3springboot.dto.PostDto;
import com.spring.boot.task3springboot.model.Post;
import com.spring.boot.task3springboot.vm.PostVmRequest;
import com.spring.boot.task3springboot.vm.PostVmResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AppUserMapper.class)
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "user", target = "user")
    PostDto toPostDto(Post post);

    PostVmResponse toPostVmResponse(Post post);

    @Mapping(target = "user", ignore = true)
    Post toPost(PostVmResponse postVmResponse);

    @Mapping(source = "user", target = "user")
    Post toPost(PostDto postDto);

    Post toPost(PostVmRequest postVmRequest);
}
