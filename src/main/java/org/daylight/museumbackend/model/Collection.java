package org.daylight.museumbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
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
    @JsonBackReference
    private List<Item> items;
}
