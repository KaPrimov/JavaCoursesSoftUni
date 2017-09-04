package weddingplanner.weddingplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import weddingplanner.weddingplanner.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person AS p " +
            "WHERE concat(p.firstName, ' ', p.middleNameInitial, ' ', p.lastName) = :fullName ")
    Person findPersonByFullName(@Param("fullName") String fullName);

}
