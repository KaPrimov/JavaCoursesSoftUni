package com.softuni.carDealer.services.apis;

import java.util.List;

import com.softuni.carDealer.dtos.binding.add.SupplierAddDto;
import com.softuni.carDealer.dtos.binding.relations.SupplierDto;
import com.softuni.carDealer.dtos.view.LocalSupplierView;

public interface SupplierService<Supplier, Long> {
    void save(SupplierAddDto supplierDao);

    List<SupplierDto> findAllDtos();

    List<LocalSupplierView> findAllLocalSuppliers();
}
