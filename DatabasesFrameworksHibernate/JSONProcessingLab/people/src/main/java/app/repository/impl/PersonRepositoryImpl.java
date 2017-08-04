package app.repository.impl;

import app.domain.model.Person;
import app.domain.model.PhoneNumber;
import app.repository.PersonRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created by User on 1.8.2017 г..
 */
@Repository
public class PersonRepositoryImpl implements PersonRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public Person merge(Person person) {
        return em.merge(person);
    }
}
