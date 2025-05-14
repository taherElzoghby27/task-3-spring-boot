package com.spring.boot.task3springboot.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseVmWithoutPost {
    private Long id;
    private String name;
    private int age;
}
