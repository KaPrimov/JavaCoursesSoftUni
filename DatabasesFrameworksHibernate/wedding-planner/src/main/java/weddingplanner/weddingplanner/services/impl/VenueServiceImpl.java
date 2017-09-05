package weddingplanner.weddingplanner.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weddingplanner.weddingplanner.dto.binding.xml.AddVenueXml;
import weddingplanner.weddingplanner.dto.binding.xml.AddVenuesXmlWrapper;
import weddingplanner.weddingplanner.dto.view.xml.VenuesInSofiaDto;
import weddingplanner.weddingplanner.dto.view.xml.VenuesInSofiaXmlWrapper;
import weddingplanner.weddingplanner.entities.Venue;
import weddingplanner.weddingplanner.repositories.VenueRepository;
import weddingplanner.weddingplanner.services.api.VenueService;
import weddingplanner.weddingplanner.utils.DTOConvertUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public VenuesInSofiaXmlWrapper findAllVenuesInSofia() {
        List<Object[]> venues = this.venueRepository.weddingsInSofia();
        VenuesInSofiaXmlWrapper xmlWrapper = new VenuesInSofiaXmlWrapper();
        List<VenuesInSofiaDto> venuesDtos = new ArrayList<>();
        for (Object[] venue : venues) {
            String name = venue[0].toString();
            BigInteger capacity = BigInteger.valueOf(Long.parseLong(venue[1].toString().substring(0, venue[1].toString().lastIndexOf("."))));
            Integer count = Integer.valueOf(venue[2].toString());
            VenuesInSofiaDto venuesInSofiaDto = new VenuesInSofiaDto(name, capacity, count);
            venuesDtos.add(venuesInSofiaDto);
        }
        xmlWrapper.setVenuesDtos(venuesDtos);
        return xmlWrapper;
    }
}
