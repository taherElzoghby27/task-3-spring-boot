package com.spring.boot.task3springboot.service;

import com.spring.boot.task3springboot.dto.AccountDto;
import com.spring.boot.task3springboot.vm.AccountResponseVm;
import jakarta.transaction.SystemException;

public interface AuthService {
    AccountResponseVm login(AccountDto accountDto) throws SystemException;

    AccountResponseVm signup(AccountDto accountDto) throws SystemException;
}
