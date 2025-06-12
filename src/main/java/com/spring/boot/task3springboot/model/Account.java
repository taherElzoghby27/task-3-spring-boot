package com.spring.boot.task3springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(schema = "hr")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "hr", uniqueConstraints = @UniqueConstraint(columnNames = {"ACCOUNTS_ID", "ROLES_ID"}))
    private List<Role> roles;
}
