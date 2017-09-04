package weddingplanner.weddingplanner.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weddingplanner.weddingplanner.dto.binding.json.AddAgencyDto;
import weddingplanner.weddingplanner.dto.view.json.OrderedAgencyDto;
import weddingplanner.weddingplanner.dto.view.xml.*;
import weddingplanner.weddingplanner.entities.Agency;
import weddingplanner.weddingplanner.entities.Cash;
import weddingplanner.weddingplanner.entities.Gift;
import weddingplanner.weddingplanner.entities.Invitation;
import weddingplanner.weddingplanner.repositories.AgencyRepository;
import weddingplanner.weddingplanner.repositories.InvitationRepository;
import weddingplanner.weddingplanner.repositories.PresentRepository;
import weddingplanner.weddingplanner.services.api.AgencyService;
import weddingplanner.weddingplanner.utils.DTOConvertUtil;
import weddingplanner.weddingplanner.utils.DTOValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    private final PresentRepository presentRepository;
    private final InvitationRepository invitationRepository;
    private final DTOValidator dtoValidator;

    @Autowired
    public AgencyServiceImpl(AgencyRepository agencyRepository, PresentRepository presentRepository, InvitationRepository invitationRepository, DTOValidator dtoValidator) {
        this.agencyRepository = agencyRepository;
        this.presentRepository = presentRepository;
        this.invitationRepository = invitationRepository;
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

    @Override
    public List<OrderedAgencyDto> findAllOrderedAgencies() {
        List<Agency> agencies = this.agencyRepository.findAllByOrderByEmployeesCountDescNameAsc();
        List<OrderedAgencyDto> orderedAgencyDtos = new ArrayList<>();
        for (Agency agency : agencies) {
            orderedAgencyDtos.add(DTOConvertUtil.convert(agency, OrderedAgencyDto.class));
        }

        return orderedAgencyDtos;
    }

    @Override
    public AgenciesByTownXmlWrapper findAllAgenciesByTown() {
        AgenciesByTownXmlWrapper xmlWrapper = new AgenciesByTownXmlWrapper();
        List<String> towns = this.agencyRepository.findAllTownsWithNamesBiggerThanSixSymbols();
        List<TownXmlViewDto> townsXmlDtos = new ArrayList<>();
        for (String town : towns) {
            List<Agency> allAgenciesAtTown = this.agencyRepository.findAllAgenciesAtTown(town);
            if(allAgenciesAtTown.size() == 0) {
                continue;
            }
            TownXmlViewDto townXmlViewDto = new TownXmlViewDto();
            townXmlViewDto.setName(town);
            List<AgencyXmlViewDto> agencyViewDtoList = new ArrayList<>();
            for (Agency agency : allAgenciesAtTown) {
                AgencyXmlViewDto agencyXmlViewDto = DTOConvertUtil.convert(agency, AgencyXmlViewDto.class);
                List<WeddingViewXmlDto> weddingViewXmlDtos = new ArrayList<>();
                for (WeddingViewXmlDto weddingViewXmlDto : agencyXmlViewDto.getWeddings()) {
                    List<Invitation> invitations = this.invitationRepository.findAllByWeddingId(weddingViewXmlDto.getId());
                    long count = invitations.stream().map(Invitation::getPresent)
                            .filter(p -> p instanceof Gift).count();
                    BigDecimal sum = invitations.stream().map(Invitation::getPresent)
                            .filter(p -> p instanceof Cash)
                            .map(c -> (Cash)c)
                            .map(Cash::getAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    weddingViewXmlDto.setCash(sum);
                    weddingViewXmlDto.setCount(count);
                    List<GuestXmlViewDto> guestXmlViewDtos = new ArrayList<>();
                    for (Invitation invitation : invitations) {
                        GuestXmlViewDto guestXmlViewDto = DTOConvertUtil.convert(invitation, GuestXmlViewDto.class);
                        guestXmlViewDtos.add(guestXmlViewDto);
                    }
                    weddingViewXmlDto.setGuestXmlViewDtos(guestXmlViewDtos);
                    weddingViewXmlDtos.add(weddingViewXmlDto);
                }
                agencyXmlViewDto.setWeddings(weddingViewXmlDtos);
                agencyViewDtoList.add(agencyXmlViewDto);
            }
            townXmlViewDto.setAgencyXmlViewDtos(agencyViewDtoList);
            townsXmlDtos.add(townXmlViewDto);

        }
        xmlWrapper.setTownXmlViewDtos(townsXmlDtos);
        return xmlWrapper;
    }
}
