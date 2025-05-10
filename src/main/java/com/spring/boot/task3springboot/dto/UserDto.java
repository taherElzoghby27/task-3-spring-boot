package com.spring.boot.task3springboot.dto;
import com.spring.boot.task3springboot.vm.PostVmResponse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "error.field.empty")
    @Size(min = 7, message = "error.field.size.name")
    private String name;
    @NotEmpty(message = "error.field.empty.password")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "password.format")
    private String password;
    @Min(value = 18, message = "error.field.age")
    @NotNull(message = "error.field.empty")
    private int age;
    private List<PostVmResponse> posts;
}
