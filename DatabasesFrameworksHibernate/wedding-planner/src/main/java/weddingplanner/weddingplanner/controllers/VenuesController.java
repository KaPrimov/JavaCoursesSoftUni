package weddingplanner.weddingplanner.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import weddingplanner.weddingplanner.dto.binding.xml.AddVenuesXmlWrapper;
import weddingplanner.weddingplanner.dto.view.xml.VenuesInSofiaXmlWrapper;
import weddingplanner.weddingplanner.io.Serializer;
import weddingplanner.weddingplanner.services.api.VenueService;

@Controller
public class VenuesController {

    private static final String VENUES_INPUT_PATH = "/input/venues.xml";
    private static final String VENUES_OUTPUT = "src/main/resources/out/sofia-venues.xml";

    private final Serializer xmlSerializer;
    private final VenueService venueService;

    public VenuesController(@Qualifier("XmlParser") Serializer xmlSerializer, VenueService venueService) {
        this.xmlSerializer = xmlSerializer;
        this.venueService = venueService;
    }

    public void saveVenues() {
        AddVenuesXmlWrapper addVenuesXmlWrapper = this.xmlSerializer.deserialize(AddVenuesXmlWrapper.class, VENUES_INPUT_PATH);
        this.venueService.saveVenues(addVenuesXmlWrapper);
    }

    public void findWeddingsInSofia() {
        VenuesInSofiaXmlWrapper allVenuesInSofia = this.venueService.findAllVenuesInSofia();
        this.xmlSerializer.serialize(allVenuesInSofia, VENUES_OUTPUT);
    }
}
