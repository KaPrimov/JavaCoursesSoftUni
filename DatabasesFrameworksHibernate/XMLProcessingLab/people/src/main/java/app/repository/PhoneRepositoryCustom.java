package app.repository;

import app.domain.model.PhoneNumber;

/**
 * Created by User on 1.8.2017 г..
 */
public interface PhoneRepositoryCustom {
    PhoneNumber merge(PhoneNumber phoneNumber);
}
