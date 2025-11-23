package org.daylight.museumbackend.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    private Integer birthYear;
    private Integer deathYear;

    @Column(length = 100)
    private String country;
}
