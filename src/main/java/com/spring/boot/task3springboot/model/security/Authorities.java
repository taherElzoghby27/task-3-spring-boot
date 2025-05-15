package com.spring.boot.task3springboot.model.security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "authorities", schema = "hr",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "authority"})
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String authority;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
