package com.softuni.residentEvil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softuni.residentEvil.entities.Capital;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, Long> {
	
	Capital findByName(String name);
}
