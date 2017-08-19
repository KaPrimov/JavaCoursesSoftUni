package softuni.services.api;

import softuni.dto.binding.AddTownJsonDto;
import softuni.dto.view.xml.wrappers.TownsAndPopulationXmlWrapper;

public interface TownService {
    void saveTowns(AddTownJsonDto[] townJsonDto);

    TownsAndPopulationXmlWrapper townsAndClients();
}
