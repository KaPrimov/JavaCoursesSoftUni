package weddingplanner.weddingplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import weddingplanner.weddingplanner.dto.binding.json.AddAgencyDto;
import weddingplanner.weddingplanner.io.Serializer;
import weddingplanner.weddingplanner.services.api.AgencyService;

@Controller
public class AgencyController {

    private static final String AGENCIES_INPUT_PATH = "/input/agencies.json";

    private final Serializer jsonSerializer;
    private final AgencyService agencyService;

    @Autowired
    public AgencyController(@Qualifier("JsonParser") Serializer jsonSerializer, AgencyService agencyService) {
        this.jsonSerializer = jsonSerializer;
        this.agencyService = agencyService;
    }

    public void importAgencies() {
        AddAgencyDto[] addAgencyDtos = this.jsonSerializer.deserialize(AddAgencyDto[].class, AGENCIES_INPUT_PATH);
        this.agencyService.saveAgencies(addAgencyDtos);
    }
}
