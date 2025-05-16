package com.spring.boot.task3springboot.config;

import com.spring.boot.task3springboot.dto.UserSecurityDto;
import com.spring.boot.task3springboot.service.UserAuthSecurityService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserAuthSecurityService userAuthSecurityService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();
            UserSecurityDto user = userAuthSecurityService.getUserByUserName(username);
            if (!password.equals(user.getPassword()) || !username.equals(user.getUsername())) {
                throw new SystemException("error.invalid.credentials");
            }
            List<SimpleGrantedAuthority> roles = user.getAuthorities().stream()
                    .map(authority ->
                            new SimpleGrantedAuthority("ROLE_" + authority.getAuthority()))
                    .toList();
            return new UsernamePasswordAuthenticationToken(username, password, roles);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
