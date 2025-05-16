package com.spring.boot.task3springboot.mapper;

import com.spring.boot.task3springboot.dto.AccountDto;
import com.spring.boot.task3springboot.model.Account;
import com.spring.boot.task3springboot.vm.AccountResponseVm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE_ACCOUNT = Mappers.getMapper(AccountMapper.class);

    AccountDto toAccountDto(Account account);

    Account toAccount(AccountDto accountDto);

    AccountResponseVm toAccountResponseVm(AccountDto accountDto);
}
