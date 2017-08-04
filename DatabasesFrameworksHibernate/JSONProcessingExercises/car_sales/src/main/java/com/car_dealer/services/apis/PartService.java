package com.car_dealer.services.apis;

import com.car_dealer.dtos.binding.add.PartAddDto;
import com.car_dealer.dtos.binding.relations.PartDto;

import java.util.List;

public interface PartService<Part, Long> {
    void save(PartAddDto partAddDto);

    List<PartDto> findAllPartDtos();
}
