package softuni.photography.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.photography.dto.bindingModels.add.AddWorkshopXmlDto;
import softuni.photography.dto.bindingModels.add.PhotographerInWorkshopDto;
import softuni.photography.dto.bindingModels.add.xmlWrappers.WorkshopXmlWrapper;
import softuni.photography.entities.Photographer;
import softuni.photography.entities.Workshop;
import softuni.photography.repositories.PhotographerRepository;
import softuni.photography.repositories.WorkshopRepository;
import softuni.photography.services.api.WorkshopService;
import softuni.photography.utils.CustomValidator;
import softuni.photography.utils.DTOConvertUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final PhotographerRepository photographerRepository;

    @Autowired
    public WorkshopServiceImpl(WorkshopRepository workshopRepository, PhotographerRepository photographerRepository) {
        this.workshopRepository = workshopRepository;
        this.photographerRepository = photographerRepository;
    }

    @Override
    public void save(WorkshopXmlWrapper workshopXmlWrapper) {
        for (AddWorkshopXmlDto addWorkshopXmlDto : workshopXmlWrapper.getAddWorkshopXmlDtos()) {
            Workshop workshop = DTOConvertUtil.convert(addWorkshopXmlDto, Workshop.class);
            Photographer trainer = this.photographerRepository.findPhotographerByFullName(addWorkshopXmlDto.getTrainerFullName());
            List<PhotographerInWorkshopDto> innerPhotographers = addWorkshopXmlDto.getParticipants();
            List<Photographer> participants = new ArrayList<>();
            if (innerPhotographers != null) {

                for (PhotographerInWorkshopDto innerPhotographer : innerPhotographers) {
                    String fullName= innerPhotographer.getFirstName() + " " + innerPhotographer.getLastName();
                    Photographer photographer = this.photographerRepository.findPhotographerByFullName(fullName);
                    participants.add(photographer);
                }
            }
            workshop.setParticipants(participants);
            workshop.setTrainer(trainer);
            if (CustomValidator.isValid(workshop)) {
                this.workshopRepository.saveAndFlush(workshop);
                System.out.println("Successfully imported " + workshop.getName());
            } else {
                System.out.println("Error. Invalid data provided");
            }
        }
    }
}
