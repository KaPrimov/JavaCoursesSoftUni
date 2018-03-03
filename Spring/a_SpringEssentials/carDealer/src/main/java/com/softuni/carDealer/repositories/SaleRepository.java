package com.softuni.carDealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softuni.carDealer.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
