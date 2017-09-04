package weddingplanner.weddingplanner.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weddingplanner.weddingplanner.dto.binding.json.AddAgencyDto;
import weddingplanner.weddingplanner.entities.Agency;
import weddingplanner.weddingplanner.repositories.AgencyRepository;
import weddingplanner.weddingplanner.services.api.AgencyService;
import weddingplanner.weddingplanner.utils.DTOConvertUtil;
import weddingplanner.weddingplanner.utils.DTOValidator;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    private final DTOValidator dtoValidator;

    @Autowired
    public AgencyServiceImpl(AgencyRepository agencyRepository, DTOValidator dtoValidator) {
        this.agencyRepository = agencyRepository;
        this.dtoValidator = dtoValidator;
    }

    @Override
    public void saveAgencies(AddAgencyDto[] addAgencyDto) {
        Agency[] agencies = DTOConvertUtil.convert(addAgencyDto, Agency[].class);
        for (Agency agency : agencies) {
            if(this.dtoValidator.isValid(agency)) {
                System.out.println("Successfully imported " + agency.getName());
                this.agencyRepository.save(agency);
            } else {
                System.out.println("Error!");
            }
        }
    }
}
