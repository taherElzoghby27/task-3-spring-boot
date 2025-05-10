package com.spring.boot.task3springboot.service;

import com.spring.boot.task3springboot.dto.PostDto;
import com.spring.boot.task3springboot.vm.PostVmRequest;
import jakarta.transaction.SystemException;

import java.util.List;

public interface PostService {
    List<PostDto> getPosts();

    PostDto getPostById(Long id) throws SystemException;

    PostDto createPost(PostVmRequest postVmRequest) throws SystemException;

    void deletePostById(Long id) throws SystemException;

    PostDto updatePost(PostDto postDto) throws SystemException;

    List<PostDto> getPostsByUsers();

    PostDto getPostByIdWithUser(Long id) throws Exception;
}
