package weddingplanner.weddingplanner.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weddingplanner.weddingplanner.dto.binding.xml.AddPresentDto;
import weddingplanner.weddingplanner.dto.binding.xml.AddPresentXmlWrapper;
import weddingplanner.weddingplanner.entities.Cash;
import weddingplanner.weddingplanner.entities.Gift;
import weddingplanner.weddingplanner.entities.Invitation;
import weddingplanner.weddingplanner.entities.Present;
import weddingplanner.weddingplanner.entities.enums.Size;
import weddingplanner.weddingplanner.repositories.InvitationRepository;
import weddingplanner.weddingplanner.repositories.PresentRepository;
import weddingplanner.weddingplanner.services.api.PresentService;
import weddingplanner.weddingplanner.utils.DTOConvertUtil;
import weddingplanner.weddingplanner.utils.DTOValidator;

@Service
@Transactional
public class PresentServiceImpl implements PresentService {

    private final PresentRepository presentRepository;
    private final InvitationRepository invitationRepository;
    private final DTOValidator dtoValidator;

    @Autowired
    public PresentServiceImpl(PresentRepository presentRepository, InvitationRepository invitationRepository, DTOValidator dtoValidator) {
        this.presentRepository = presentRepository;
        this.invitationRepository = invitationRepository;
        this.dtoValidator = dtoValidator;
    }

    @Override
    public void savePresents(AddPresentXmlWrapper addPresentXmlWrapper) {
        for (AddPresentDto addPresentDto : addPresentXmlWrapper.getPresentDtoList()) {
            Invitation invitation = this.invitationRepository.findOne(addPresentDto.getInvitationId());

            if (invitation == null || addPresentDto.getType() == null) {
                System.out.println("Error. Invalid data provided");
                continue;
            }
            Present present = null;
            if (addPresentDto.getType().equalsIgnoreCase("cash")) {
                present = DTOConvertUtil.convert(addPresentDto, Cash.class);
            } else if (addPresentDto.getType().equalsIgnoreCase("gift")) {
                present = DTOConvertUtil.convert(addPresentDto, Gift.class);
            } else {
                System.out.println("Error! Invalid data provided");
                continue;
            }
            present.setOwner(invitation.getGuest());
            if (present instanceof Gift) {
                if (addPresentDto.getSize() == null) {
                    System.out.println("Error. Invalid data provided");
                    continue;
                }
                ((Gift) present).setSize(Size.NOT_SPECIFIED);
                if (contains(addPresentDto.getSize().toUpperCase())) {
                    ((Gift) present).setSize(Size.valueOf(addPresentDto.getSize().toUpperCase()));
                }
            }

            if (this.dtoValidator.isValid(present)) {
                invitation.setPresent(present);
                this.invitationRepository.save(invitation);
                System.out.println("Succesfully imported gift from " + present.getOwner().getFullName());
            } else {
                System.out.println("Error! Invalid data provided");
            }
        }
    }

    private static boolean contains(String sizeToTest) {
        for (Size size : Size.values()) {
            if (size.name().equals(sizeToTest)) {
                return true;
            }
        }
        return false;
    }
}
