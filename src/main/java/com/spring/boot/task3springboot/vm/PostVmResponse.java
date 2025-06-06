package com.spring.boot.task3springboot.vm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostVmResponse {
    private Long id;
    private String text;
    private String imagePath;
    private UserResponseVmWithoutPost user;
}
