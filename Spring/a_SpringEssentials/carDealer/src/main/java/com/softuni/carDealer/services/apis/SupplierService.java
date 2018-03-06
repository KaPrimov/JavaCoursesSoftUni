package com.softuni.carDealer.services.apis;

import java.util.List;

import com.softuni.carDealer.dtos.binding.add.SupplierAddDto;
import com.softuni.carDealer.dtos.binding.relations.SupplierDto;
import com.softuni.carDealer.dtos.view.LocalSupplierView;
import com.softuni.carDealer.dtos.view.SupplierView;

public interface SupplierService<Supplier, Long> {
    void save(SupplierAddDto supplierDao);

    List<SupplierDto> findAllDtos();

    List<LocalSupplierView> findAllLocalSuppliers();
    
    List<LocalSupplierView> findAllImportSuppliers();

	SupplierDto getById(java.lang.Long supplierId);

	List<SupplierView> allSuppliers();

	void upldateSupplier(SupplierDto supplierToEdit);

	void delete(Long id);
}
