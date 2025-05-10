package com.spring.boot.task3springboot.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PostVmResponse {
    private Long id;
    @NotEmpty(message = "error.field.empty")
    @Size(min = 15, message = "error.field.size.text")
    private String text;
    @NotEmpty(message = "error.field.empty.image")
    @JsonProperty("image_path")
    private String imagePath;
    private UserResponseVm user;
}
