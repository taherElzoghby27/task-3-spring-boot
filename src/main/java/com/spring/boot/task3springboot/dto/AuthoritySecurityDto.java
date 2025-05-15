package com.spring.boot.task3springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthoritySecurityDto {
    private Long id;
    private String username;
    private String authority;
}
