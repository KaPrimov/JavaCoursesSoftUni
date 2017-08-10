package softuni.photography.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.photography.dto.bindingModels.add.AddAccessoryXmlDto;
import softuni.photography.entities.Accessory;
import softuni.photography.repositories.AccessoryRepository;
import softuni.photography.repositories.PhotographerRepository;
import softuni.photography.services.api.AccessoryService;
import softuni.photography.utils.CustomValidator;
import softuni.photography.utils.DTOConvertUtil;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;
    private final PhotographerRepository photographerRepository;


    @Autowired
    public AccessoryServiceImpl(AccessoryRepository accessoryRepository, PhotographerRepository photographerRepository) {
        this.accessoryRepository = accessoryRepository;
        this.photographerRepository = photographerRepository;
    }

    @Override
    public void save(AddAccessoryXmlDto addAccessoryDto) {
        Long countPhotographers = this.photographerRepository.count();
        long id = ThreadLocalRandom.current().nextLong(1, countPhotographers);
        Accessory accessory = DTOConvertUtil.convert(addAccessoryDto, Accessory.class);
        if(CustomValidator.isValid(accessory)) {
            accessory.setOwner(this.photographerRepository.findOne(id));
            this.accessoryRepository.save(accessory);
            System.out.printf("Successfully imported %s%n", accessory.getName());
        }
    }
}
