package com.spring.boot.task3springboot.controller;

import com.spring.boot.task3springboot.dto.UserDto;
import com.spring.boot.task3springboot.service.UserService;
import com.spring.boot.task3springboot.vm.UserResponseVm;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<UserResponseVm> createUser(@RequestBody @Valid UserDto user) throws SystemException {
        return ResponseEntity.created(URI.create("/create-user")).body(userService.createUser(user));
    }

    @PutMapping("/update-user")
    public ResponseEntity<UserResponseVm> updateUser(@RequestBody @Valid UserDto user) throws SystemException {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @GetMapping("/get-user")
    public ResponseEntity<UserResponseVm> getUser(@RequestParam @Valid Long id) throws SystemException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UserResponseVm>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<Void> deleteUser(@RequestParam @Valid Long id) throws SystemException {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user-with-all-posts")
    public ResponseEntity<UserResponseVm> getUserWithAllPosts(@RequestParam @Valid Long id) throws SystemException {
        return ResponseEntity.ok(userService.getUserWithAllPosts(id));
    }
}
