package app.service;

import app.domain.dto.PersonDto;
import app.domain.model.Person;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public interface PersonService {

    void create(Person person);

    Person findById(long id);

    List<Person> findByCountry(String country);

    void store (Person person);
}
