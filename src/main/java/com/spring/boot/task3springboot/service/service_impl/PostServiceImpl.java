package com.spring.boot.task3springboot.service.service_impl;

import com.spring.boot.task3springboot.dto.PostDto;
import com.spring.boot.task3springboot.dto.UserDto;
import com.spring.boot.task3springboot.mapper.AppUserMapper;
import com.spring.boot.task3springboot.mapper.PostMapper;
import com.spring.boot.task3springboot.model.Post;
import com.spring.boot.task3springboot.model.User;
import com.spring.boot.task3springboot.repository.PostRepo;
import com.spring.boot.task3springboot.service.PostService;
import com.spring.boot.task3springboot.service.UserService;
import com.spring.boot.task3springboot.vm.PostVmRequest;
import com.spring.boot.task3springboot.vm.UserResponseVm;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserService userService;

    @Override
    public List<PostDto> getPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(PostMapper.INSTANCE_POST::toPostDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) throws SystemException {
        if (Objects.isNull(id)) {
            throw new SystemException("error.id.must.be.notnull");
        }
        Optional<Post> post = postRepo.findById(id);
        if (post.isEmpty()) {
            throw new SystemException("error.post.notfound");
        }
        return PostMapper.INSTANCE_POST.toPostDto(post.get());
    }

    @Override
    public PostDto createPost(PostVmRequest postVmRequest) throws SystemException {
        if (Objects.nonNull(postVmRequest.getId())) {
            throw new SystemException("error.id.must.be.notnull");
        }
        return getPostDto(postVmRequest);
    }

    @Override
    public void deletePostById(Long id) throws SystemException {
        if (Objects.isNull(id)) {
            throw new SystemException("error.id.must.be.notnull");
        }
        postRepo.deleteById(id);
    }

    @Override
    public PostDto updatePost(PostVmRequest postVmRequest) throws SystemException {
        if (Objects.isNull(postVmRequest.getId())) {
            throw new SystemException("error.id.must.be.notnull");
        }
        if (Objects.isNull(getPostById(postVmRequest.getId()))) {
            throw new SystemException("error.post.notfound");
        }
        return getPostDto(postVmRequest);
    }

    private PostDto getPostDto(PostVmRequest postVmRequest) throws SystemException {
        try {
            Post post = PostMapper.INSTANCE_POST.toPost(postVmRequest);
            UserResponseVm userResponseVm = userService.getUserById(postVmRequest.getUserId());
            User user = AppUserMapper.INSTANCE_USER.toUser(userResponseVm);
            post.setUser(user);
            post = postRepo.save(post);
            return PostMapper.INSTANCE_POST.toPostDto(post);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SystemException("something.went.wrong");
        }
    }

    @Override
    public List<PostDto> getPostsByUsers() {
        List<Post> posts = postRepo.getPostsByUsers();
        return posts.stream().map(PostMapper.INSTANCE_POST::toPostDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostByIdWithUser(Long id) throws Exception {
        Post post = postRepo.getPostByIdWithUser(id);
        if (Objects.isNull(post)) {
            throw new Exception("error.post.notfound");
        }
        return PostMapper.INSTANCE_POST.toPostDto(post);
    }
}
