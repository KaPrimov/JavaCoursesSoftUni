package weddingplanner.weddingplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import weddingplanner.weddingplanner.entities.Agency;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    Agency findFirstByName(String name);

    List<Agency> findAllByOrderByEmployeesCountDescNameAsc();

    @Query("SELECT DISTINCT a.town FROM Agency AS a " +
            "WHERE length(a.town) >= 6 ")
    List<String> findAllTownsWithNamesBiggerThanSixSymbols();

    @Query("SELECT a FROM Agency AS a " +
            "WHERE a.weddings.size >= 2 AND " +
            "a.town=:town")
    List<Agency> findAllAgenciesAtTown(@Param("town") String town);
}
