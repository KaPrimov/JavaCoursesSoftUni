package com.softuni.carDealer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softuni.carDealer.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
	List<Sale> findAllById(Long id);
	
    
    @Query(
    		"SELECT s FROM Sale AS s " +
    		"WHERE s.discount > 0"
	)
    List<Sale> salesWithAnyDiscount();
    
    @Query(
    		"SELECT s FROM Sale AS s " +
    		"WHERE s.discount = :discountValue"
	)
    List<Sale> salesWithExactDiscount(@Param("discountValue") double discountValue);
}
