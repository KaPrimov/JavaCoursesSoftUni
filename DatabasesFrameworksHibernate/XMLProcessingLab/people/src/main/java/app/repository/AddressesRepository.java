package app.repository;

import app.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 2.8.2017 г..
 */
@Repository
public interface AddressesRepository extends JpaRepository<Address, Long> {
}
