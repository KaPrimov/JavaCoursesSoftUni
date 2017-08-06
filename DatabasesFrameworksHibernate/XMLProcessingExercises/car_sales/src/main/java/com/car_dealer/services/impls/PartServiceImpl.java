package com.car_dealer.services.impls;

import com.car_dealer.dtos.binding.add.PartAddDto;
import com.car_dealer.dtos.binding.relations.PartDto;
import com.car_dealer.entities.Part;
import com.car_dealer.repositories.PartRepository;
import com.car_dealer.services.apis.PartService;
import com.car_dealer.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
