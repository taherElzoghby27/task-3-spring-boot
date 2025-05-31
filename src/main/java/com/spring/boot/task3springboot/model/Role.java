package com.spring.boot.task3springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "hr")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "hr",uniqueConstraints = @UniqueConstraint(columnNames = {"ACCOUNTS_ID","ROLES_ID"}))
    private List<Account> accounts;

}