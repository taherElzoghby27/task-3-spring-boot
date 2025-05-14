package com.spring.boot.task3springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    //memory level
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user1 = User.withUsername("user").password("{noop}password").roles("ADMIN", "USER").build();
        UserDetails user2 = User.withUsername("user2").password("{noop}password2").roles("ADMIN").build();
        UserDetails user3 = User.withUsername("user3").password("{bcrypt}$2a$12$ygRKXseSJFOHv9ftcQhyne9CwJCKMkJPFNt9LFjPmS99YwLi2l0zy")
                .roles("USER").build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                authorizeRequests ->
                        authorizeRequests.requestMatchers(HttpMethod.GET, "/users/**", "/posts/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/users/**", "/posts/**").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/users/**", "/posts/**").hasRole("USER")
                                .requestMatchers(HttpMethod.DELETE, "/users/**", "/posts/**").hasRole("USER")
        );
        http.httpBasic(httpBasic -> Customizer.withDefaults());
        return http.build();
    }
}
