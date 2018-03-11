package com.softuni.residentEvil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softuni.residentEvil.entities.Virus;

@Repository
public interface VirusRepository extends JpaRepository<Virus, String> {
	
	Virus findByName(String name);

}
