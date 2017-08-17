package softuni.photography.services.api;

import softuni.photography.dto.bindingModels.add.xmlWrappers.WorkshopXmlWrapper;
import softuni.photography.dto.viewModels.LocationsDtoXmlWrapper;

public interface WorkshopService {
    void save(WorkshopXmlWrapper workshopXmlWrapper);

   LocationsDtoXmlWrapper workshopsByLocation();

}
