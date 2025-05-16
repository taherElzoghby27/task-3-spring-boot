package com.spring.boot.task3springboot.service;

import com.spring.boot.task3springboot.dto.AccountDto;
import jakarta.transaction.SystemException;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto) throws SystemException;
    AccountDto getAccountByUserName(String username) throws SystemException;
}
