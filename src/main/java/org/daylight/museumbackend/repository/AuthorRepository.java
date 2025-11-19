package org.daylight.museumbackend.repository;

import org.daylight.museumbackend.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
