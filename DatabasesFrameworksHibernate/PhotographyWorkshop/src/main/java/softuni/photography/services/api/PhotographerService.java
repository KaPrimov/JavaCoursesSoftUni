package softuni.photography.services.api;

import softuni.photography.dto.bindingModels.add.AddPhotographerJsonDto;
import softuni.photography.dto.viewModels.LandscapePhotographersDto;
import softuni.photography.dto.viewModels.OrderedPhotographerDto;
import softuni.photography.entities.Photographer;

import java.util.List;

public interface PhotographerService {
    void save(AddPhotographerJsonDto photographerJsonDto, Iterable<Long> lensesIds);
    Photographer findPhotographerByFullName(String fullName);
    List<OrderedPhotographerDto> findAllPhotographersOrderedByFirstNameAscLastNameDesc();
    List<LandscapePhotographersDto> findAllLandscapePhotographers();
    List<Photographer> photographersWithSameCameras();
}
