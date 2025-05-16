package com.spring.boot.task3springboot.setting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "token")
@Setter
@Getter
public class JWTToken {
    private String secret;
    private Duration time;
}
