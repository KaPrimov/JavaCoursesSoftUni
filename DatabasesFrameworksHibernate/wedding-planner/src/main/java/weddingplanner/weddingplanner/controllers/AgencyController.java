package weddingplanner.weddingplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import weddingplanner.weddingplanner.dto.binding.json.AddAgencyDto;
import weddingplanner.weddingplanner.dto.view.json.OrderedAgencyDto;
import weddingplanner.weddingplanner.dto.view.xml.AgenciesByTownXmlWrapper;
import weddingplanner.weddingplanner.io.Serializer;
import weddingplanner.weddingplanner.services.api.AgencyService;

import java.util.List;

@Controller
public class AgencyController {

    private static final String AGENCIES_INPUT_PATH = "/input/agencies.json";
    private static final String ORDERED_AGENCIES_OUTPUT = "src/main/resources/out/ordered-agencies.json";
    private static final String AGENCIES_BY_TOWN_OUTPUT = "src/main/resources/out/agencies-by-town.xml";

    private final Serializer jsonSerializer;
    private final AgencyService agencyService;
    private final Serializer xmlSerializer;

    @Autowired
    public AgencyController(@Qualifier("JsonParser") Serializer jsonSerializer, AgencyService agencyService,@Qualifier("XmlParser") Serializer xmlSerializer) {
        this.jsonSerializer = jsonSerializer;
        this.agencyService = agencyService;
        this.xmlSerializer = xmlSerializer;
    }

    public void importAgencies() {
        AddAgencyDto[] addAgencyDtos = this.jsonSerializer.deserialize(AddAgencyDto[].class, AGENCIES_INPUT_PATH);
        this.agencyService.saveAgencies(addAgencyDtos);
    }

    public void saveOrderedAgencies() {
        List<OrderedAgencyDto> allOrderedAgencies = this.agencyService.findAllOrderedAgencies();
        this.jsonSerializer.serialize(allOrderedAgencies, ORDERED_AGENCIES_OUTPUT);
    }

    public void saveAllAgenciesByTown() {
        AgenciesByTownXmlWrapper allAgenciesByTown = this.agencyService.findAllAgenciesByTown();
        this.xmlSerializer.serialize(allAgenciesByTown, AGENCIES_BY_TOWN_OUTPUT);
    }
}
