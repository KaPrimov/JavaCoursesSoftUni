package com.softuni.carDealer.services.impls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softuni.carDealer.dtos.binding.add.SupplierAddDto;
import com.softuni.carDealer.dtos.binding.relations.SupplierDto;
import com.softuni.carDealer.dtos.view.LocalSupplierView;
import com.softuni.carDealer.entities.Supplier;
import com.softuni.carDealer.repositories.SupplierRepository;
import com.softuni.carDealer.services.apis.SupplierService;
import com.softuni.carDealer.utils.ModelParser;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository carRepository) {
        this.supplierRepository = carRepository;
    }

    @Override
    public void save(SupplierAddDto supplierDao) {
        Supplier supplier = ModelParser.getInstance().map(supplierDao, Supplier.class);
        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public List<SupplierDto> findAllDtos() {
        List<Supplier> suppliers = this.supplierRepository.findAll();
        List<SupplierDto> supplierDtos = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            supplierDtos.add(ModelParser.getInstance().map(supplier, SupplierDto.class));
        }
        return supplierDtos;
    }

    @Override
    public List<LocalSupplierView> findAllLocalSuppliers() {
        return this.supplierRepository.findAllLocalSuppliers();
    }

	@Override
	public List<LocalSupplierView> findAllImportSuppliers() {
		return this.supplierRepository.findAllImportSuppliers();
	}

	@Override
	public SupplierDto getById(Long supplierId) {		
		return ModelParser.getInstance().map(this.supplierRepository.getOne(supplierId), SupplierDto.class);
	}
}
