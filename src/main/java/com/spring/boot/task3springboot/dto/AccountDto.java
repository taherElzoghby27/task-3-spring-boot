package com.spring.boot.task3springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AccountDto {
    private Long id;
    private String username;
    private String phoneNumber;
    private String password;
    private List<RoleDto> roles;
}
