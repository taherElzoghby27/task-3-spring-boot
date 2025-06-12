package com.spring.boot.task3springboot.service.service_impl;

import com.spring.boot.task3springboot.dto.AccountDto;
import com.spring.boot.task3springboot.dto.RoleDto;
import com.spring.boot.task3springboot.enums.RoleEnum;
import com.spring.boot.task3springboot.mapper.AccountMapper;
import com.spring.boot.task3springboot.mapper.RoleMapper;
import com.spring.boot.task3springboot.model.Account;
import com.spring.boot.task3springboot.model.Role;
import com.spring.boot.task3springboot.repository.AccountRepo;
import com.spring.boot.task3springboot.service.AccountService;
import com.spring.boot.task3springboot.service.RoleService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Override
    public AccountDto createAccount(AccountDto accountDto) throws SystemException {
        try {
            if (Objects.nonNull(accountDto.getId())) {
                throw new SystemException("error.id.must.be.null");
            }
            Account account = AccountMapper.INSTANCE_ACCOUNT.toAccount(accountDto);
            account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
            RoleDto roleDto = roleService.findByRole(RoleEnum.USER.toString());
            Role role = RoleMapper.ROLE_MAPPER.toRole(roleDto);
            List<Role> roles = account.getRoles();
            if (Objects.isNull(roles)) {
                roles = new ArrayList<>();
            }
            roles.add(role);
            account.setRoles(roles);
            account = accountRepo.save(account);
            return AccountMapper.INSTANCE_ACCOUNT.toAccountDto(account);
        } catch (Exception e) {
            throw new SystemException("something.went.wrong");
        }
    }

    @Override
    public AccountDto getAccountByUserName(String username) throws SystemException {
        Optional<Account> result = accountRepo.findByUsername(username);
        if (result.isEmpty()) {
            throw new SystemException("error.user.notfound");
        }
        return AccountMapper.INSTANCE_ACCOUNT.toAccountDto(result.get());
    }
}
