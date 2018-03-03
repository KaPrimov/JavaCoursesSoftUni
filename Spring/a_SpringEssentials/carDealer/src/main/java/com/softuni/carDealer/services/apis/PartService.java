package com.softuni.carDealer.services.apis; 
import java.util.List;

import com.softuni.carDealer.dtos.binding.add.PartAddDto;
import com.softuni.carDealer.dtos.binding.relations.PartDto;

public interface PartService<Part, Long> {
    void save(PartAddDto partAddDto);

    List<PartDto> findAllPartDtos();
}
