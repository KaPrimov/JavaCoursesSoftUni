package com.softuni.carDealer.services.impls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softuni.carDealer.dtos.binding.add.PartAddDto;
import com.softuni.carDealer.dtos.binding.relations.PartDto;
import com.softuni.carDealer.entities.Part;
import com.softuni.carDealer.repositories.PartRepository;
import com.softuni.carDealer.repositories.SupplierRepository;
import com.softuni.carDealer.services.apis.PartService;
import com.softuni.carDealer.utils.ModelParser;

@Service
@Transactional
public class PartServiceImpl implements PartService<Part, Long> {
    
	private final PartRepository partRepository;
	private final SupplierRepository supplierRepository;
	
    @Autowired
    public PartServiceImpl(PartRepository carRepository, SupplierRepository supplierRepository) {
        this.partRepository = carRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void save(PartAddDto partAddDto) {
        Part part = ModelParser.getInstance().map(partAddDto, Part.class);
        part.setSupplier(supplierRepository.getOne(partAddDto.getSupplier()));
        part.setQuantity(1L);
        this.partRepository.save(part);
    }

    @Override
    public List<PartDto> findAllPartDtos() {
        List<Part> parts = this.partRepository.findAll();
        List<PartDto> partDtos = new ArrayList<>();

        for (Part part : parts) {
            partDtos.add(ModelParser.getInstance().map(part, PartDto.class));
        }
        return partDtos;
    }

	@Override
	public PartDto getById(Long id) {
		return ModelParser.getInstance().map(this.partRepository.getOne(id), PartDto.class);
	}

	@Override
	public void updatePart(PartAddDto partAddDto) {
		Part part = this.partRepository.getOne(partAddDto.getId());
		part.setName(partAddDto.getName());
		part.setPrice(partAddDto.getPrice());
		part.setQuantity(partAddDto.getQuantity());
		part.setSupplier(this.supplierRepository.getOne(partAddDto.getSupplier()));
		
		this.partRepository.save(part);
	}

	@Override
	public void delete(Long id) {
		this.partRepository.deleteById(id);		
	}
}
