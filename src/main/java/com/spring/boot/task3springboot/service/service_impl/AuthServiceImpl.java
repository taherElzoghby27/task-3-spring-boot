package com.spring.boot.task3springboot.service.service_impl;

import com.spring.boot.task3springboot.config.TokenHandler;
import com.spring.boot.task3springboot.dto.AccountDto;
import com.spring.boot.task3springboot.mapper.AccountMapper;
import com.spring.boot.task3springboot.repository.AuthRepo;
import com.spring.boot.task3springboot.service.AccountService;
import com.spring.boot.task3springboot.service.AuthService;
import com.spring.boot.task3springboot.service.RoleService;
import com.spring.boot.task3springboot.vm.AccountResponseVm;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthRepo authRepo;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenHandler tokenHandler;

    @Override
    public AccountResponseVm login(AccountDto accountDto) throws SystemException {
        AccountDto accDto = accountService.getAccountByUserName(accountDto.getUsername());
        if (!passwordEncoder.matches(accountDto.getPassword(), accDto.getPassword())) {
            throw new SystemException("error.invalid.credentials");
        }
        AccountResponseVm accountResponseVm = AccountMapper.INSTANCE_ACCOUNT.toAccountResponseVm(accDto);
        accountResponseVm.setToken(tokenHandler.generateToken(accountDto));
        return accountResponseVm;

    }

    @Override
    public AccountResponseVm signup(AccountDto accountDto) throws SystemException {
        accountDto = accountService.createAccount(accountDto);
        AccountResponseVm accountResponseVm = AccountMapper.INSTANCE_ACCOUNT.toAccountResponseVm(accountDto);
        accountResponseVm.setToken(tokenHandler.generateToken(accountDto));
        return accountResponseVm;
    }
}
