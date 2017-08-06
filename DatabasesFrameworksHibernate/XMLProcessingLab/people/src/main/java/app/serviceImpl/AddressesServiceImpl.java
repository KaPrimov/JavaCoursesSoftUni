package app.serviceImpl;

import app.domain.model.Address;
import app.repository.AddressesRepository;
import app.service.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by User on 2.8.2017 г..
 */
@Service
@Transactional
public class AddressesServiceImpl implements AddressesService {

    @Autowired
    private AddressesRepository addressesRepository;

    @Override
    public List<Address> findAllAdresses() {
        return addressesRepository.findAll();
    }
}
