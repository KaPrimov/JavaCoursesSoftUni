package app.repository.impl;

import app.domain.model.PhoneNumber;
import app.repository.PhoneRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created by User on 1.8.2017 г..
 */
@Repository
public class PhoneRepositoryImpl implements PhoneRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public PhoneNumber merge(PhoneNumber phoneNumber) {
        return em.merge(phoneNumber);
    }
}
