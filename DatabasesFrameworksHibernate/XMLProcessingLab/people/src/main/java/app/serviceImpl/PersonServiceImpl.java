package app.serviceImpl;

import app.domain.dto.AddressDto;
import app.domain.dto.PersonDto;
import app.domain.dto.PhoneDto;
import app.domain.model.Person;
import app.domain.model.PhoneNumber;
import app.repository.PersonRepository;
import app.repository.PhoneRepository;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityListeners;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private EntityManagerFactory emf;


    @Override
    public void create(Person person) {
        this.personRepository.save(person);
    }

    @Override
    @Transactional
    public Person findById(long id) {
        Person person = this.personRepository.findOne(id);
        return person;
    }

    @Override
    @Transactional
    public List<Person> findByCountry(String country) {
        List<Person> persons = this.personRepository.findByCountry(country);
        return persons;
    }

    @Override
    public void store(Person person) {
        boolean hasSavedPhones = false;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            for (Iterator<PhoneNumber> i = person.getPhoneNumbers().iterator(); i.hasNext(); ) {
                PhoneNumber phoneNumber = i.next();
                try {
                    phoneRepository.save(phoneNumber);
                    hasSavedPhones = true;
                } catch (ConstraintViolationException e) {
                    i.remove();
                    System.out.println("Invalid number detected. Not stored in DB : " + phoneNumber.getNumber());
                }
            }


            personRepository.save(person);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

}
