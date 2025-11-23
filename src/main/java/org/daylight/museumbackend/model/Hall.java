package org.daylight.museumbackend.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    private Integer floor;

    @Column(columnDefinition = "TEXT")
    private String description;
}
