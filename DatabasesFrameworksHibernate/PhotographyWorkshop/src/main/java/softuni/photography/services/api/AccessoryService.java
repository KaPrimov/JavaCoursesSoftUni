package softuni.photography.services.api;

import softuni.photography.dto.bindingModels.add.AddAccessoryXmlDto;

public interface AccessoryService {
    void save(AddAccessoryXmlDto accessoryXmlDto);
}
