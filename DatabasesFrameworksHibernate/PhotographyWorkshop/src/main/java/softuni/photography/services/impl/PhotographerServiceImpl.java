package softuni.photography.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.photography.dto.bindingModels.add.AddPhotographerJsonDto;
import softuni.photography.dto.viewModels.LandscapePhotographersDto;
import softuni.photography.dto.viewModels.OrderedPhotographerDto;
import softuni.photography.entities.Lens;
import softuni.photography.entities.Photographer;
import softuni.photography.repositories.CameraRepository;
import softuni.photography.repositories.LensRepository;
import softuni.photography.repositories.PhotographerRepository;
import softuni.photography.services.api.PhotographerService;
import softuni.photography.utils.DTOConvertUtil;
import softuni.photography.utils.DTOValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class PhotographerServiceImpl implements PhotographerService {

    private final PhotographerRepository photographerRepository;

    private final LensRepository lensRepository;

    private final CameraRepository cameraRepository;

    @Autowired
    private final DTOValidator dtoValidator;

    @Autowired
    public PhotographerServiceImpl(PhotographerRepository photographerRepository, LensRepository lensRepository, CameraRepository cameraRepository, DTOValidator dtoValidator) {

        this.photographerRepository = photographerRepository;
        this.lensRepository = lensRepository;
        this.cameraRepository = cameraRepository;
        this.dtoValidator = dtoValidator;
    }


    @Override
    public void save(AddPhotographerJsonDto photographerJsonDto, Iterable<Long> lensesIds) {
        Photographer photographer = DTOConvertUtil.convert(photographerJsonDto, Photographer.class);
        addCameras(photographer);
        if(dtoValidator.isValid(photographer)) {
            List<Lens> resultLenses = new ArrayList<>();
            List<Lens> lenses = this.lensRepository.findAllByIdIn(lensesIds);
            for (Lens lens : lenses) {
                if(lens.getCompatibleWith() != null &&
                        (lens.getCompatibleWith().equalsIgnoreCase(photographer.getPrimaryCamera().getMake()) ||
                                lens.getCompatibleWith().equalsIgnoreCase(photographer.getSecondaryCamera().getMake()))) {
                    if (lens.getOwners() == null) {
                        lens.setOwners(new HashSet<>());
                    }
                    lens.getOwners().add(photographer);
                    resultLenses.add(lens);
                }
            }
            photographer.setLenses(new HashSet<>(lenses));
            this.photographerRepository.save(photographer);
            System.out.printf("Successfully imported %s %s | Lenses: %d%n", photographer.getFirstName(), photographer.getLastName(), resultLenses.size());
        } else {
            System.out.println("Error. Invalid data provided");
        }

    }

    @Override
    public Photographer findPhotographerByFullName(String fullName) {
        return this.photographerRepository.findPhotographerByFullName(fullName);
    }

    @Override
    public List<OrderedPhotographerDto> findAllPhotographersOrderedByFirstNameAscLastNameDesc() {
        List<Photographer> orderedPhotographers = this.photographerRepository.findAllByOrderByFirstNameAscLastNameDesc();
        return DTOConvertUtil.convert(orderedPhotographers, OrderedPhotographerDto.class);
    }

    @Override
    public List<LandscapePhotographersDto> findAllLandscapePhotographers() {

        List<Photographer> photographersWithDslr = this.photographerRepository.landscapePhotographers();
        List<LandscapePhotographersDto> landscapePhotographersDtos = new ArrayList<>();
        for (Photographer photographer : photographersWithDslr) {
            boolean isLandscapePhotographer = false;
            for (Lens lens : photographer.getLenses()) {
                if (lens.getFocalLength() <= 30) {
                    isLandscapePhotographer = true;
                    break;
                }
            }
            if(isLandscapePhotographer) {
                landscapePhotographersDtos.add(new LandscapePhotographersDto(photographer.getFirstName(),
                        photographer.getLastName(), photographer.getPrimaryCamera().getMake(), photographer.getLenses().size()));
            }
        }
        return landscapePhotographersDtos;
    }

    @Override
    public List<Photographer> photographersWithSameCameras() {
        return this.photographerRepository.photographersWithSameCameras();
    }

    private void addCameras(Photographer photographer) {
        Long count = this.lensRepository.count();
        long id = ThreadLocalRandom.current().nextLong(1, count);
        if(photographer.getPrimaryCamera() == null) {
            photographer.setPrimaryCamera(this.cameraRepository.findOne(id));
        }
        id = ThreadLocalRandom.current().nextLong(1, count);
        if (photographer.getSecondaryCamera() == null) {
            photographer.setSecondaryCamera(this.cameraRepository.findOne(id));
        }
    }
}
