package com.spring.boot.task3springboot.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AccountResponseVm {
    private Long id;
    private String username;
    private String phoneNumber;
    private String token;
}
