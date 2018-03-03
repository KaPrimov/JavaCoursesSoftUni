package com.softuni.carDealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softuni.carDealer.entities.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}
