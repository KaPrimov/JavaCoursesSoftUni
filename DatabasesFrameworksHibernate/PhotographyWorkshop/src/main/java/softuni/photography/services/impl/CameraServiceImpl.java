package softuni.photography.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.photography.entities.Camera;
import softuni.photography.repositories.CameraRepository;
import softuni.photography.services.api.CameraService;
import softuni.photography.utils.DTOValidator;

@Service
@Transactional
public class CameraServiceImpl implements CameraService {

    private final CameraRepository cameraRepository;

    private final DTOValidator dtoValidator;

    @Autowired
    public CameraServiceImpl(CameraRepository cameraRepository, DTOValidator dtoValidator) {
        this.cameraRepository = cameraRepository;
        this.dtoValidator = dtoValidator;
    }

    @Override
    public void save(Camera camera) {

        if (dtoValidator.isValid(camera)) {
            this.cameraRepository.save(camera);
            System.out.printf("Successfully imported %s %s %s%n", camera.getType(), camera.getMake(), camera.getModel());
        } else {
            System.out.println("Error. Invalid data provided");
        }

    }
}
