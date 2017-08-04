package app.repository;

import app.domain.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PhoneRepository extends JpaRepository<PhoneNumber, Long>, PhoneRepositoryCustom {

}
