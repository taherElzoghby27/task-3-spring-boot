package com.spring.boot.task3springboot.controller;
import com.spring.boot.task3springboot.dto.PostDto;
import com.spring.boot.task3springboot.service.PostService;
import com.spring.boot.task3springboot.vm.PostVmRequest;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/allposts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/post")
    public ResponseEntity<PostDto> getPostById(@RequestParam @Valid Long id) throws SystemException {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping("/create-post")
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostVmRequest postVmRequest) throws SystemException {
        return ResponseEntity.created(URI.create("/create-post")).body(postService.createPost(postVmRequest));
    }

    @DeleteMapping("/delete-post")
    public ResponseEntity<Void> deletePost(@RequestParam @Valid Long id) throws SystemException {
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-post")
    public ResponseEntity<PostDto> updatePost(@RequestBody @Valid PostVmRequest postVmRequest) throws SystemException {
        return ResponseEntity.ok(postService.updatePost(postVmRequest));
    }

    @GetMapping("/posts-by-user")
    public ResponseEntity<List<PostDto>> getPostsByUsers() {
        return ResponseEntity.ok(postService.getPostsByUsers());
    }

    @GetMapping("/post-by-id")
    public ResponseEntity<PostDto> getPostByIdWithUsers(@RequestParam @Valid Long id) throws Exception {
        return ResponseEntity.ok(postService.getPostByIdWithUser(id));
    }
}
