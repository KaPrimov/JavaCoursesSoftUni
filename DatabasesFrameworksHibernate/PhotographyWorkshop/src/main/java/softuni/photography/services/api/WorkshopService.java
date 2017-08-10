package softuni.photography.services.api;

import softuni.photography.dto.bindingModels.add.xmlWrappers.WorkshopXmlWrapper;

public interface WorkshopService {
    void save(WorkshopXmlWrapper workshopXmlWrapper);
}
