package com.spring.boot.task3springboot.dto;

import com.spring.boot.task3springboot.vm.UserResponseVmWithoutPost;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    @NotEmpty(message = "error.field.empty")
    @Size(min = 15, message = "error.field.size.text")
    private String text;
    @NotEmpty(message = "error.field.empty.image")
    private String imagePath;
    private UserResponseVmWithoutPost user;
}
