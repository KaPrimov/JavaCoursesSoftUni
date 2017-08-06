package com.car_dealer.repositories;

import com.car_dealer.dtos.view.LocalSupplierView;
import com.car_dealer.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT DISTINCT new com.car_dealer.dtos.view.LocalSupplierView(s.id, s.name, s.parts.size) FROM Supplier AS s INNER JOIN s.parts " +
            "WHERE s.importer=false ")
    List<LocalSupplierView> findAllLocalSuppliers();
}
