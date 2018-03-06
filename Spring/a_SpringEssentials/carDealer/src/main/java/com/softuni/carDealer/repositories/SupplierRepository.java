package com.softuni.carDealer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softuni.carDealer.dtos.view.LocalSupplierView;
import com.softuni.carDealer.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT DISTINCT new com.softuni.carDealer.dtos.view.LocalSupplierView(s.id, s.name, s.parts.size) FROM Supplier AS s INNER JOIN s.parts " +
            "WHERE s.isImporter=false ")
    List<LocalSupplierView> findAllLocalSuppliers();
    
    @Query("SELECT DISTINCT new com.softuni.carDealer.dtos.view.LocalSupplierView(s.id, s.name, s.parts.size) FROM Supplier AS s INNER JOIN s.parts " +
            "WHERE s.isImporter=true")
    List<LocalSupplierView> findAllImportSuppliers();
}
