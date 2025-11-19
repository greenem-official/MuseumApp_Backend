package org.daylight.museumbackend.repository;

import org.daylight.museumbackend.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

}
