package org.daylight.museumbackend.repository;


import org.daylight.museumbackend.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNameContainingIgnoreCase(String name);

    List<Item> findByYear(Integer year);

    List<Item> findByCollection_Id(Long collectionId);

    List<Item> findByAuthor_Id(Long authorId);

    List<Item> findByHall_Id(Long hallId);
    Page<Item> findAll(Specification<Item> spec, Pageable pageable);
}
