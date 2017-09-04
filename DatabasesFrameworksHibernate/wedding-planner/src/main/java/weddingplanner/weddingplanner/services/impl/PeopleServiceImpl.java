package weddingplanner.weddingplanner.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weddingplanner.weddingplanner.dto.binding.json.AddPersonDto;
import weddingplanner.weddingplanner.entities.Person;
import weddingplanner.weddingplanner.entities.enums.Gender;
import weddingplanner.weddingplanner.repositories.PersonRepository;
import weddingplanner.weddingplanner.services.api.PeopleService;
import weddingplanner.weddingplanner.utils.DTOConvertUtil;
import weddingplanner.weddingplanner.utils.DTOValidator;

@Service
@Transactional
public class PeopleServiceImpl implements PeopleService {

    private final PersonRepository personRepository;
    private final DTOValidator dtoValidator;

    @Autowired
    public PeopleServiceImpl(PersonRepository personRepository, DTOValidator dtoValidator) {
        this.personRepository = personRepository;
        this.dtoValidator = dtoValidator;
    }

    @Override
    public void addPeople(AddPersonDto[] personDtos) {
        for (AddPersonDto personDto : personDtos) {
            if (personDto.getGender() == null) {
                personDto.setGender(Gender.NOT_SPECIFIED);
            }
            Person person = DTOConvertUtil.convert(personDto, Person.class);
            if (this.dtoValidator.isValid(person)) {
                System.out.println("Successfully imported " + person.getFullName());
                this.personRepository.save(person);
            } else {
                System.out.println("Error. Invalid data provided");
            }
        }
    }
}
