package com.spring.boot.task3springboot.config;

import com.spring.boot.task3springboot.config.filters.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableMethodSecurity//for PreAuthorize
public class SecurityConfig {
    //    @Autowired
//    private UserDetailsService userDetailsService;
    @Autowired
    private AuthFilter authFilters;

    //memory level
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails user1 = User.withUsername("user").password("{noop}password").roles("ADMIN", "USER").build();
//        UserDetails user2 = User.withUsername("user2").password("{noop}password2").roles("ADMIN").build();
//        UserDetails user3 = User.withUsername("user3").password("{bcrypt}$2a$12$Xrky55VFJHJZKc64DX7UuOMXehMdxFBtpRiKL7R.TtgCetF8AlWse")
//                .roles("USER").build();
//        return new InMemoryUserDetailsManager(user1, user2, user3);
//    }
    //database level
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.sessionManagement(
//                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                authRequest -> authRequest.requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
        );
//                                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/accounts/**").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/users/**", "/posts/**").hasAnyRole("ADMIN", "USER")
//                                .requestMatchers(HttpMethod.POST, "/users/**", "/posts/**").hasAnyRole("USER", "ADMIN")
//                                .requestMatchers(HttpMethod.PUT, "/users/**", "/posts/**").hasRole("USER")
//                                .requestMatchers(HttpMethod.DELETE, "/users/**", "/posts/**").hasRole("USER")


        http.addFilterBefore(authFilters, UsernamePasswordAuthenticationFilter.class);
        //http.httpBasic(httpBasic -> Customizer.withDefaults());//for basic auth
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() throws Exception {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        return authenticationProvider;
//    }
}
