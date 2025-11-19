package org.daylight.museumbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(length = 100)
    private String fullName;

    @Column(length = 30, nullable = false)
    @Convert(converter = UserRole.UserRoleConverter.class)
    private UserRole role;
}
