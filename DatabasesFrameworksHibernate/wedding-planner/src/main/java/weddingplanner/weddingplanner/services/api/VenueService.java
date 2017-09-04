package weddingplanner.weddingplanner.services.api;

import weddingplanner.weddingplanner.dto.binding.xml.AddVenuesXmlWrapper;

public interface VenueService {
    void saveVenues(AddVenuesXmlWrapper addVenuesXmlWrapper);
}
