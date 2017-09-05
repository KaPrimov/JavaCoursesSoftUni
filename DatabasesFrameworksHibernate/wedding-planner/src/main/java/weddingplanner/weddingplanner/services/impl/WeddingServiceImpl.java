package weddingplanner.weddingplanner.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weddingplanner.weddingplanner.dto.binding.json.AddGuestDto;
import weddingplanner.weddingplanner.dto.binding.json.AddWeddingDto;
import weddingplanner.weddingplanner.dto.view.json.WeddingGuestsDto;
import weddingplanner.weddingplanner.entities.*;
import weddingplanner.weddingplanner.repositories.*;
import weddingplanner.weddingplanner.services.api.WeddingService;
import weddingplanner.weddingplanner.utils.DTOConvertUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class WeddingServiceImpl implements WeddingService {

    private final WeddingRepository weddingRepository;
    private final PersonRepository personRepository;
    private final AgencyRepository agencyRepository;
    private final InvitationRepository invitationRepository;
    private final VenueRepository venueRepository;
    private final DTOValidator dtoValidator;

    @Autowired
    public WeddingServiceImpl(WeddingRepository weddingRepository, PersonRepository personRepository, AgencyRepository agencyRepository, InvitationRepository invitationRepository, VenueRepository venueRepository, DTOValidator dtoValidator) {
        this.weddingRepository = weddingRepository;
        this.personRepository = personRepository;
        this.agencyRepository = agencyRepository;
        this.invitationRepository = invitationRepository;
        this.venueRepository = venueRepository;
        this.dtoValidator = dtoValidator;
    }


    @Override
    public void saveWeddings(AddWeddingDto[] addWeddingDtos) {
        for (AddWeddingDto addWeddingDto : addWeddingDtos) {
            Person bride = this.personRepository.findPersonByFullName(addWeddingDto.getBride());
            Person bridegroom = this.personRepository.findPersonByFullName(addWeddingDto.getBridegroom());
            Agency agency = this.agencyRepository.findFirstByName(addWeddingDto.getAgency());
            Wedding wedding = DTOConvertUtil.convert(addWeddingDto, Wedding.class);
            wedding.setAgency(agency);
            wedding.setBride(bride);
            wedding.setBridegroom(bridegroom);
            if(this.dtoValidator.isValid(wedding)) {
                this.weddingRepository.save(wedding);
                System.out.println("Successfully imported wedding of " + wedding.getBride().getFirstName() + " and " + wedding.getBridegroom().getFirstName());
                for (AddGuestDto addGuestDto : addWeddingDto.getGuests()) {
                    Person person = this.personRepository.findPersonByFullName(addGuestDto.getName());
                    if(person == null) {
                        continue;
                    }
                    Invitation invitation = DTOConvertUtil.convert(addGuestDto, Invitation.class);
                    invitation.setWedding(wedding);
                    invitation.setGuest(person);
                    if(this.dtoValidator.isValid(invitation)) {
                        this.invitationRepository.save(invitation);
                    }
                }
            } else {
                System.out.println("Error. Invalid data provided.");
            }
        }
    }

    @Override
    public void addVenues() {
        List<Venue> venues = this.venueRepository.findAll();
        List<Wedding> weddings = this.weddingRepository.findAll();
        Random rnd = new Random();
        for (Wedding wedding : weddings) {
            List<Venue> temp = new ArrayList<>();
            Venue venue = venues.get(rnd.nextInt(venues.size()));
            Venue venue2 = venues.get(rnd.nextInt(venues.size()));
            venue.getWeddings().add(wedding);
            venue2.getWeddings().add(wedding);
            temp.add(venue);
            temp.add(venue2);
            wedding.setVenues(temp);
            this.weddingRepository.save(wedding);
            this.venueRepository.save(venue);
            this.venueRepository.save(venue2);
        }
    }

    @Override
    public List<WeddingGuestsDto> findAllGuestsToWedding() {
        List<WeddingGuestsDto> weddingGuests = new ArrayList<>();
        List<Wedding> weddings = this.weddingRepository.findAll();
        for (Wedding wedding : weddings) {
            List<Invitation> invitations = this.invitationRepository.findAllByWeddingId(wedding.getId());
            WeddingGuestsDto weddingGuestsDto = DTOConvertUtil.convert(wedding, WeddingGuestsDto.class);
            Long invitedGuests = (long) invitations.size();
            Long brideGuests = invitations.stream().filter(i -> i.getFamily().equalsIgnoreCase("bride")).count();
            Long bridegroomGuests = invitations.stream().filter(i -> i.getFamily().equalsIgnoreCase("bridegroom")).count();
            Long attendingGuests = invitations.stream().filter(Invitation::isAttending).count();
            weddingGuestsDto.setAttendingGuests(attendingGuests);
            weddingGuestsDto.setBrideGuests(brideGuests);
            weddingGuestsDto.setBridegroomGuests(bridegroomGuests);
            weddingGuestsDto.setInvitedGuests(invitedGuests);
            weddingGuestsDto.setGuests(invitations.stream()
                .map(i -> i.getGuest().getFullName()).toArray(String[]::new));
            weddingGuests.add(weddingGuestsDto);
        }

        weddingGuests.sort(Comparator.comparing(WeddingGuestsDto::getInvitedGuests)
                .reversed()
                .thenComparing(WeddingGuestsDto::getAttendingGuests));
        return weddingGuests;
    }
}
