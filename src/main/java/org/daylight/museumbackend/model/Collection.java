package org.daylight.museumbackend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "curator_id")
    private User curator;

    private LocalDate startDate; // TODO maybe change date format and what about conversion
    private LocalDate endDate;

    @OneToMany(mappedBy = "collection")
    private List<Item> items;
}
