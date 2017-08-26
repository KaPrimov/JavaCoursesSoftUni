package softuni.photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.photography.entities.Photographer;

import java.util.List;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Long> {
    @Query("SELECT p FROM Photographer AS p " +
            "WHERE concat(p.firstName, ' ', p.lastName) = :fullName")
    Photographer findPhotographerByFullName(@Param("fullName") String fullName);

    List<Photographer> findAllByOrderByFirstNameAscLastNameDesc();


    @Query("SELECT p FROM Photographer AS p INNER JOIN p.primaryCamera AS c " +
            "WHERE camera_type = 'DSLR' " +
            "ORDER BY p.firstName, p.lastName DESC")
    List<Photographer> landscapePhotographers();

    @Query("SELECT DISTINCT p FROM Photographer AS p " +
            "LEFT  JOIN p.lenses AS l " +
            "INNER JOIN p.primaryCamera " +
            "WHERE p.primaryCamera.make = p.secondaryCamera.make")
    List<Photographer> photographersWithSameCameras();

}
