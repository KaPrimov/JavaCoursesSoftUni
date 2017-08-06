package app.service;

import app.domain.model.Address;

import java.util.List;

/**
 * Created by User on 2.8.2017 г..
 */
public interface AddressesService {

    List<Address> findAllAdresses ();
}
