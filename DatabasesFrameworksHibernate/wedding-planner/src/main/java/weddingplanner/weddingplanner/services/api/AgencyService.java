package weddingplanner.weddingplanner.services.api;

import weddingplanner.weddingplanner.dto.binding.json.AddAgencyDto;
import weddingplanner.weddingplanner.dto.view.json.OrderedAgencyDto;
import weddingplanner.weddingplanner.dto.view.xml.AgenciesByTownXmlWrapper;

import java.util.List;

public interface AgencyService {
    void saveAgencies(AddAgencyDto[] addAgencyDto);

    List<OrderedAgencyDto> findAllOrderedAgencies();

    AgenciesByTownXmlWrapper findAllAgenciesByTown();
}
