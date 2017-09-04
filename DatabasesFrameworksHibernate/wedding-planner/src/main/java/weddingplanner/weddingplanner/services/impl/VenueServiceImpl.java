package weddingplanner.weddingplanner.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weddingplanner.weddingplanner.dto.binding.xml.AddVenueXml;
import weddingplanner.weddingplanner.dto.binding.xml.AddVenuesXmlWrapper;
import weddingplanner.weddingplanner.entities.Venue;
import weddingplanner.weddingplanner.repositories.VenueRepository;
import weddingplanner.weddingplanner.services.api.VenueService;
import weddingplanner.weddingplanner.utils.DTOConvertUtil;
import weddingplanner.weddingplanner.utils.DTOValidator;

@Service
@Transactional
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;
    private final DTOValidator dtoValidator;


    @Autowired
    public VenueServiceImpl(VenueRepository venueRepository, DTOValidator dtoValidator) {
        this.venueRepository = venueRepository;
        this.dtoValidator = dtoValidator;
    }

    @Override
    public void saveVenues(AddVenuesXmlWrapper addVenuesXmlWrapper) {
        for (AddVenueXml addVenueXml : addVenuesXmlWrapper.getAddVenueXmls()) {
            Venue venue = DTOConvertUtil.convert(addVenueXml, Venue.class);
            if(this.dtoValidator.isValid(venue)) {
                System.out.println("Successfully imported " + addVenueXml.getName());
                this.venueRepository.save(venue);
            } else {
                System.out.println("Error!");
            }
        }
    }
}
