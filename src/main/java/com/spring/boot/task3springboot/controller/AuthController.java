package com.spring.boot.task3springboot.controller;

import com.spring.boot.task3springboot.dto.AccountDto;
import com.spring.boot.task3springboot.service.AuthService;
import com.spring.boot.task3springboot.vm.AccountResponseVm;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AccountResponseVm> login(@RequestBody @Valid AccountDto accountDto) throws SystemException {
        return ResponseEntity.ok(authService.login(accountDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<AccountResponseVm> signup(@RequestBody @Valid AccountDto accountDto) throws SystemException {
        return ResponseEntity.ok(authService.signup(accountDto));
    }
}
