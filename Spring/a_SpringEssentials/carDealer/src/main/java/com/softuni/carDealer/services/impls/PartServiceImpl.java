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
import com.softuni.carDealer.services.apis.PartService;
import com.softuni.carDealer.utils.ModelParser;

@Service
@Transactional
public class PartServiceImpl implements PartService<Part, Long> {
    private final PartRepository partRepository;

    @Autowired
    public PartServiceImpl(PartRepository carRepository) {
        this.partRepository = carRepository;
    }

    @Override
    public void save(PartAddDto partAddDto) {
        Part part = ModelParser.getInstance().map(partAddDto, Part.class);
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
}
