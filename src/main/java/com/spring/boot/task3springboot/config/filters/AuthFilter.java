package com.spring.boot.task3springboot.config.filters;

import com.spring.boot.task3springboot.config.TokenHandler;
import com.spring.boot.task3springboot.dto.AccountDto;
import com.spring.boot.task3springboot.dto.UserSecurityDto;
import com.spring.boot.task3springboot.mapper.AccountMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    @Lazy
    private TokenHandler tokenHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization");
            if (Objects.isNull(token) || !token.startsWith("Bearer ")) {
                response.reset();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            token = token.substring(7);
            AccountDto userValidated = null;

            userValidated = tokenHandler.validateToken(token);

            if (Objects.isNull(userValidated)) {
                response.reset();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            List<SimpleGrantedAuthority> roles = userValidated.getRoles().stream()
                    .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority.getRole()))
                    .toList();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userValidated,
                    userValidated.getPassword(),
                    roles);
            //set to security context
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            //complete filter
            filterChain.doFilter(request, response);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.contains("auth");
    }
}
