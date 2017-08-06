package app.domain.validation;

import app.domain.model.PhoneNumber;

import javax.persistence.PrePersist;

/**
 * Created by User on 1.8.2017 г..
 */
public class PhoneNumberListerner {

    @PrePersist
    public void validate(PhoneNumber phoneNumber) {
        System.out.println(phoneNumber);
    }
}
