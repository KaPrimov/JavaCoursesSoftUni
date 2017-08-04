package com.car_dealer.services.apis;

import com.car_dealer.dtos.binding.add.SupplierAddDto;
import com.car_dealer.dtos.binding.relations.SupplierDto;
import com.car_dealer.dtos.view.LocalSupplierView;

import java.util.List;

public interface SupplierService<Supplier, Long> {
    void save(SupplierAddDto supplierDao);

    List<SupplierDto> findAllDtos();

    List<LocalSupplierView> findAllLocalSuppliers();
}
