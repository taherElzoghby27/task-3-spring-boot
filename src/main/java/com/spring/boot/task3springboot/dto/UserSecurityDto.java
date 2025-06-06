package com.spring.boot.task3springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserSecurityDto {
    private Long id;
    private String username;
    private String password;
    private String enabled;
    private List<AuthoritySecurityDto> authorities;
}
