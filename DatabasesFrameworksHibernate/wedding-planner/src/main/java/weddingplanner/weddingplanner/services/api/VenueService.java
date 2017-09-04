package weddingplanner.weddingplanner.services.api;

import weddingplanner.weddingplanner.dto.binding.xml.AddVenuesXmlWrapper;
import weddingplanner.weddingplanner.dto.view.xml.VenuesInSofiaXmlWrapper;

public interface VenueService {
    void saveVenues(AddVenuesXmlWrapper addVenuesXmlWrapper);
    VenuesInSofiaXmlWrapper findAllVenuesInSofia();
}
