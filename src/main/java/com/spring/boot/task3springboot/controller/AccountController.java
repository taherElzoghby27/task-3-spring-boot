package com.spring.boot.task3springboot.controller;

import com.spring.boot.task3springboot.dto.AccountDto;
import com.spring.boot.task3springboot.service.AccountService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/create-account")
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid AccountDto accountDto) throws SystemException {
        return ResponseEntity.created(URI.create("/create-account")).body(accountService.createAccount(accountDto));
    }

    @GetMapping("/get-account-by-username")
    public ResponseEntity<AccountDto> getAccountByUserName(@RequestBody @Valid String username) throws SystemException {
        return ResponseEntity.ok(accountService.getAccountByUserName(username));
    }
}
