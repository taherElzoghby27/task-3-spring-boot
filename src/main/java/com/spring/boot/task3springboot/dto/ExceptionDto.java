package com.spring.boot.task3springboot.dto;
import com.spring.boot.task3springboot.model.BundleMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {
    private BundleMessage bundleMessage;
}
