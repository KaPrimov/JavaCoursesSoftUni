package com.softuni.carDealer.services.impls;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softuni.carDealer.dtos.binding.add.SupplierAddDto;
import com.softuni.carDealer.dtos.binding.relations.SupplierDto;
import com.softuni.carDealer.dtos.view.LocalSupplierView;
import com.softuni.carDealer.dtos.view.SupplierView;
import com.softuni.carDealer.entities.Part;
import com.softuni.carDealer.entities.Supplier;
import com.softuni.carDealer.repositories.PartRepository;
import com.softuni.carDealer.repositories.SupplierRepository;
import com.softuni.carDealer.services.apis.SupplierService;
import com.softuni.carDealer.utils.ModelParser;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService<Supplier, Long> {
	
    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    
    @Autowired
    public SupplierServiceImpl(SupplierRepository carRepository, PartRepository partRepository) {
        this.supplierRepository = carRepository;
        this.partRepository = partRepository;
    }

    @Override
    public void save(final SupplierAddDto supplierDto) {
		Supplier supplier = ModelParser.getInstance().map(supplierDto, Supplier.class);
		Set<Part> parts = new LinkedHashSet<>();
		for (Long partId : supplierDto.getParts()) {
			parts.add(partRepository.getOne(partId));
		}
		supplier.setParts(parts);
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
	public List<SupplierView> allSuppliers() {
		List<Supplier> allSuppliers = this.supplierRepository.findAll();
		List<SupplierView> views = new LinkedList<>();
		for (Supplier supplier : allSuppliers) {
			views.add(ModelParser.getInstance().map(supplier, SupplierView.class));
		}
		return views;
	}

	@Override
	public SupplierDto getById(final Long supplierId) {		
		return ModelParser.getInstance().map(this.supplierRepository.getOne(supplierId), SupplierDto.class);
	}

	@Override
	public void upldateSupplier(final SupplierDto supplierToEdit) {
		Supplier supplier = this.supplierRepository.getOne(supplierToEdit.getId());
		supplier.setIsImporter(supplierToEdit.getIsImporter());
		supplier.setName(supplierToEdit.getName());
	}

	@Override
	public void delete(final Long id) {
		Supplier supplier = this.supplierRepository.getOne(id);
		for (Part part : supplier.getParts()) {
			part.setSupplier(null);
		}
		supplier.setParts(null);
		this.supplierRepository.delete(supplier);
	}
}
