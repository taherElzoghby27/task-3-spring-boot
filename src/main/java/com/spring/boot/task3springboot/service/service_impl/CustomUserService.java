package com.spring.boot.task3springboot.service.service_impl;

import com.spring.boot.task3springboot.dto.UserSecurityDto;
import com.spring.boot.task3springboot.model.security.CustomUserDetails;
import com.spring.boot.task3springboot.service.UserAuthSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserAuthSecurityService userAuthSecurityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurityDto userSecurityDto = userAuthSecurityService.getUserByUserName(username);
        return new CustomUserDetails(userSecurityDto);
    }
}
