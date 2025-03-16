package com.ssolitim.child_tracking_system.user.model;

import static lombok.AccessLevel.PROTECTED;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "username", length = 50, unique = true)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Builder
    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
