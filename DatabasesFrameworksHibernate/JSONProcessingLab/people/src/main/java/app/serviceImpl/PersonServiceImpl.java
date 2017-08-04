package app.serviceImpl;

import app.domain.dto.json.AddressJsonDto;
import app.domain.dto.json.PersonJsonDto;
import app.domain.dto.json.PhoneJsonDto;
import app.domain.model.Person;
import app.domain.model.PhoneNumber;
import app.repository.PersonRepository;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonRepository phoneRepository;


    @Override
    public void create(Person person) {
        this.personRepository.save(person);
    }

    @Override
    public Person findById(long id) {
        Person person = this.personRepository.findOne(id);
        return person;
    }

    @Override
    public List<PersonJsonDto> findByCountry(String country) {
        List<Person> persons = this.personRepository.findByCountry(country);
        List<PersonJsonDto> personJsonDtos = new ArrayList<>();
        for (Person person : persons) {
            PersonJsonDto personJsonDto = this.convertToDto(person);
            personJsonDtos.add(personJsonDto);
        }

        return personJsonDtos;
    }

    private PersonJsonDto convertToDto(Person person){
        PersonJsonDto personJsonDto = new PersonJsonDto();
        AddressJsonDto addressJsonDto = new AddressJsonDto();
        personJsonDto.setFirstName(person.getFirstName());
        personJsonDto.setLastName(person.getLastName());
        personJsonDto.setId(person.getId());
        addressJsonDto.setCountry(person.getAddress().getCountry());
        addressJsonDto.setCity(person.getAddress().getCity());
        addressJsonDto.setStreet(person.getAddress().getCity());
        personJsonDto.setAddress(addressJsonDto);
        Set<PhoneNumber> phoneNumbers = person.getPhoneNumbers();
        for (PhoneNumber phoneNumber : phoneNumbers) {
            PhoneJsonDto phoneJsonDto = new PhoneJsonDto();
            phoneJsonDto.setNumber(phoneNumber.getNumber());
            phoneJsonDto.setPersonId(person.getId());
            personJsonDto.getPhoneNumbers().add(phoneJsonDto);
        }

        return personJsonDto;
    }
}
