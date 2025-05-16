package com.spring.boot.task3springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class Task3springbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Task3springbootApplication.class, args);
    }

}
